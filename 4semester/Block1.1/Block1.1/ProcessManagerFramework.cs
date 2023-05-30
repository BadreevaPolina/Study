using System.Diagnostics;
using System.Net.Mail;
using System.Runtime.InteropServices;

namespace Fibers
{
    public class ProcessManager
    {
        private static List <Tuple<Fiber, int>> FiberList = new();
        private static List <int> HelperPriority= new();
        private static bool Priority;
        private static Fiber? CurrentFiber;
        private static Random Random = new Random();

        static void Main()
        {
            List<Process> processList = new List<Process>(); 

            for (int i = 0; i < 3; i++)
            {
                processList.Add(new Process());
            }

            Console.WriteLine("With priority");
            RunList(processList, true);
           
            processList = new();

            for (int i = 0; i < 3; i++)
            {
                processList.Add(new Process());
            }

            Console.WriteLine("Without priority");
            RunList(processList, false);
        }
        public static void RunList(List<Process> processes, bool priority) // Add Fibers for processes and priority 
        {
            Priority = priority;

            for (var i = 0; i < processes.Count; i++)
            {
                Process process = processes[i];
                FiberList.Add(Tuple.Create(new Fiber(process.Run), process.Priority));

                for(var j = 0; j < FiberList[i].Item2 + 1; j++) // add in list priopity + 1 elems 
                {
                    HelperPriority.Add(FiberList[i].Item2);
                }
            }
            CurrentFiber = FiberList[0].Item1; // current fiber - first element
            Fiber.Switch(CurrentFiber.Id);
        }

        public static void Switch(bool fiberFinished)
        {
            if (fiberFinished) // if fiber finished
            {
                var findCurrentFiber = FiberList.Find(item => item.Item1 == CurrentFiber); // find current elem
                for(int i=0; i< findCurrentFiber.Item2 + 1; i++) // remove in list priopity + 1 elems, when the process is finished
                {
                    HelperPriority.Remove(findCurrentFiber.Item2);
                }
                FiberList.Remove(findCurrentFiber); // remove this fiber
                Console.WriteLine("OK " + FiberList.Count);
            }
            if (FiberList.Count == 0) // if FiberList.Count == 0 call the main thread
            {
                Fiber.Switch(Fiber.PrimaryId);
            }
            CurrentFiber = NextFiber(); // choice next fiber
            Fiber.Switch(CurrentFiber.Id);
         }
        public static Fiber NextFiber()
        {
            if (Priority)
            {
                var choicePriorityIndex = Random.Next(HelperPriority.Count); // choice index
                var choicePriority = HelperPriority[choicePriorityIndex]; // choice priority
                var choiceFibers = FiberList.FindAll(item => item.Item2 == choicePriority);

                return choiceFibers[Random.Next() % choiceFibers.Count].Item1;
            }
            else
            {
                return FiberList[Random.Next(FiberList.Count)].Item1; // any fiber
            }

        }
        public class Manager
        {

        }

        public class Process
        {
            private static readonly Random Rng = new Random();

            private const int LongPauseBoundary = 10000;

            private const int ShortPauseBoundary = 100;

            private const int WorkBoundary = 1000;

            private const int IntervalsAmountBoundary = 10;
            private const int PriorityLevelsNumber = 10;

            private readonly List<int> _workIntervals = new List<int>();
            private readonly List<int> _pauseIntervals = new List<int>();

            public Process()
            {
                int amount = Rng.Next(IntervalsAmountBoundary);

                for (int i = 0; i < amount; i++)
                {
                    _workIntervals.Add(Rng.Next(WorkBoundary));
                    _pauseIntervals.Add(Rng.Next(
                            Rng.NextDouble() > 0.9
                                ? LongPauseBoundary
                                : ShortPauseBoundary));
                }

                Priority = Rng.Next(PriorityLevelsNumber);
            }

            public void Run()
            {
                for (int i = 0; i < _workIntervals.Count; i++)
                {
                    Thread.Sleep(_workIntervals[i]); // work emulation
                    DateTime pauseBeginTime = DateTime.Now;
                    do
                    {
                        ProcessManager.Switch(false);
                    } while ((DateTime.Now - pauseBeginTime).TotalMilliseconds < _pauseIntervals[i]); // I/O emulation
                }
                ProcessManager.Switch(true);
            }

            public int Priority
            {
                get; private set;
            }

            public int TotalDuration
            {
                get
                {
                    return ActiveDuration + _pauseIntervals.Sum();
                }
            }

            public int ActiveDuration
            {
                get
                {
                    return _workIntervals.Sum();
                }
            }

        }
    }
}