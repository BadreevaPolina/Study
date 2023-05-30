using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Block2._1
{
    public class Consumer
    {
        private Mutex mutex = new Mutex();
        private int countConsumer;
        private List<int> buffer;
        private List<Thread> threads = new List<Thread>();
        private volatile bool stop;
        public Consumer(int countConsumer, List<int> buffer)
        {
            this.countConsumer = countConsumer;
            this.buffer = buffer; // buffer for elems
        }

        public void Run()
        {
            for (int i = 0; i < countConsumer; i++)
            {
                Thread consumerThread = new Thread(() => ConsumerFunction()); // open thread
                threads.Add(consumerThread);
                consumerThread.Start();
                Thread.Sleep(1000);
            }
        }

        private void ConsumerFunction()
        {
            while (!stop)
            {
                if (mutex.WaitOne())  // Wait for work method to signal
                {
                    if (!stop)
                    {
                        Console.WriteLine($"Consumer Remove {buffer[0]} of {Thread.CurrentThread.ManagedThreadId}");
                        buffer.Remove(buffer[0]); // remove element
                        Thread.Sleep(1000);
                    }
                    mutex.ReleaseMutex(); // release the object

                }
            }    

        }

        public void Stop() // stop all threads
        {
            stop = true;

            foreach (Thread thread in threads)
            {
                thread.Join(); // blocks the calling thread
            }

            mutex.Dispose(); // Releases all resources
            buffer.Clear(); // clear buffer
        }

    }
}
