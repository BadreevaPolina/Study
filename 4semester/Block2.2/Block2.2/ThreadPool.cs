using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using static System.Collections.Specialized.BitVector32;

namespace Block2._2
{
    public class ThreadPool : IDisposable
    {
        private const int count = 3;
        private List<Thread> threads;
        private Queue<Action> queue;
        private volatile bool stop;
        public ThreadPool()
        {
            threads = new List<Thread>();
            queue = new Queue<Action>();

            for (int i = 0; i < count; i++) // add threads
            {
                threads.Add(new Thread(Run));
                threads[i].Start();
            }
        }

        public void Enqueue(Action action)  // Lock the queue and add an task
        {
            Monitor.Enter(queue); //  block the queue

            queue.Enqueue(action);

            Monitor.Pulse(queue); // Notifies a pending thread to use the object's locked state.
            Monitor.Exit(queue); // lock is released
        }

        private void Run()
        {
            while (!stop)
            {
                Action? task = null;
                Monitor.Enter(queue); //  block the queue

                if (queue.Count > 0) // remove object and return
                {
                    task = queue.Dequeue();   
                }

                Monitor.Exit(queue); // lock is released

                task?.Invoke();
            }
        }


        public void Dispose()
        {
            Monitor.Enter(threads[0], ref stop);
            Monitor.Exit(threads[0]);
            foreach (Thread thread in threads)
            {
                thread.Join();
            }

            threads.Clear();
            queue.Clear();
        }
    }
}
