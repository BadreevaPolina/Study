using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Block2._1
{
    public class Producer
    {
        private Mutex mutex = new Mutex();
        private int countProducer;
        private List<int> buffer;
        private List<Thread> threads = new List<Thread>();
        private volatile bool stop;
        public Producer(int countProducer, List<int> buffer) // buffer for elems
        {
            this.countProducer = countProducer;
            this.buffer = buffer;
        }
        public void Run()
        {
            for (int i = 0; i < countProducer; i++)
            {
                Thread consumerThread = new Thread(() => ProducerFunction()); // open thread
                threads.Add(consumerThread);
                consumerThread.Start();
                Thread.Sleep(1000);
            }
        }

        private void ProducerFunction()
        {
            while (!stop)
            {
                if (mutex.WaitOne()) // Wait for work method to signal
                {
                    if (!stop)
                    {
                        int item = new Random().Next(100);
                        buffer.Add(item); // add element
                        Console.WriteLine($"Producer add {item} of {Thread.CurrentThread.ManagedThreadId}");
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
        }
    }
}
