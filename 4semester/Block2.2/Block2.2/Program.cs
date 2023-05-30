using System.Diagnostics;

namespace Block2._2
{
public static class Program
{
    public static void Task()
    {
        Thread.Sleep(500);
        Console.WriteLine($"Task completed on {Thread.CurrentThread.ManagedThreadId} thread");
    }

    public static void Main()
    {
        ThreadPool pool = new ThreadPool();


        for (int j = 0; j < 11; j++)
        {
            pool.Enqueue(Task); // add task
            Thread.Sleep(1000);
        }

        pool.Dispose();
    }
}
}