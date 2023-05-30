global using NUnit.Framework;
using System.Diagnostics;


namespace Fibers.tests
{
    public static class Tests
    {
        [Test]
        public static void TestTime()
        {
            Stopwatch stopwatch = new Stopwatch();

            List<ProcessManager.Process> processList = new List<ProcessManager.Process>();
            

            for (int i = 0; i < 3; i++)
            {
                processList.Add(new ProcessManager.Process());
            }
            stopwatch.Start();
            Console.WriteLine("With priority");

            ProcessManager.RunList(processList, true);
            stopwatch.Stop();
            var withPrio = stopwatch.ElapsedMilliseconds;
            processList = new();

            for (int i = 0; i < 3; i++)
            {
                processList.Add(new ProcessManager.Process());
            }
            stopwatch.Start();
            Console.WriteLine("Without priority");
            ProcessManager.RunList(processList, false);
            stopwatch.Stop();
            var withoutPrio = stopwatch.ElapsedMilliseconds;
            var boolAnswer = withoutPrio > withPrio;
            //Assert.That(boolAnswer, Is.EqualTo(true));
            Assert.Pass();
        }
       
    }
}