namespace ExamSystem
{
    internal class StripedCuckooHashSet<T> : BaseHashSet<T> where T : Tuple<long, long>
    {
        private const int ListSize = 4; // list max limit size

        private long currentSize = 0; // current amount of elements
        private volatile int capacity;
        private volatile List<T>[,] table; // table with elements
        private readonly Mutex[,] locks; // lock in each element 

        public StripedCuckooHashSet(int capacity) : base(capacity)
        {
            this.capacity = capacity;

            locks = new Mutex[2, capacity]; // create lock in each element 
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < capacity; j++)
                {
                    locks[i, j] = new Mutex();
                }
            }

            table = new List<T>[2, capacity]; // create table
            for (int i = 0; i < 2; i++)
            {
                for (int j = 0; j < capacity; j++)
                {
                    table[i, j] = new List<T>(ListSize);
                }
            }
        }

        public override bool Contains(T x)
        {
            Acquire(x); // wait one bucket
            try
            {
                List<T> set0 = table[0, Hash0(x) % capacity]; // first item in x
                if (set0.Contains(x)) // in this bucket
                {
                    return true;
                }
                else
                {
                    List<T> set1 = table[1, Hash1(x) % capacity]; // second item in x
                    if (set1.Contains(x)) // in this bucket
                    {
                        return true;
                    }
                }
                return false;
            }
            finally
            {
                Release(x);
            }
        }

        public override bool Add(T x)
        {
            Acquire(x);
            int h0 = Hash0(x) % capacity, h1 = Hash1(x) % capacity; // first and second item
            int i = -1, h = -1;
            bool mustResize = false;
            try
            {
                if (Contains(x)) return false; // check availability
                List<T> set0 = table[0, h0];
                List<T> set1 = table[1, h1];
                if (set0.Count < ListSize / 2) // if less than half full in set0 - add
                {
                    set0.Add(x);
                    Interlocked.Increment(ref currentSize);
                    return true;
                }
                else if (set1.Count < ListSize / 2) // if less than half full in set1 - add
                {
                    set1.Add(x);
                    Interlocked.Increment(ref currentSize);
                    return true;
                }
                else if (set0.Count < ListSize) // if more than half full aand less then full in set0  - add, but save h0
                {
                    set0.Add(x);
                    Interlocked.Increment(ref currentSize);
                    i = 0;
                    h = h0;
                }
                else if (set1.Count < ListSize) // if more than half full aand less then full in set1  - add, but save h1
                {
                    set1.Add(x);
                    Interlocked.Increment(ref currentSize);
                    i = 1;
                    h = h1;
                }
                else
                {
                    mustResize = true; // Nothing come out
                }
            }
            finally
            {
                Release(x);
            }

            if (mustResize) // increase thr size table
            {
                Resize();
                Add(x); /// repeat
            }
            else if (!Relocate(i, h)) // failed to move
            {
                Resize();
            }
            return true;
        }

        public override bool Remove(T x)
        {
            Acquire(x);
            try
            {
                List<T> set0 = table[0, Hash0(x) % capacity];
                if (set0.Contains(x)) // remove if contains in first item in x
                {
                    set0.Remove(x);
                    Interlocked.Decrement(ref currentSize);
                    return true;
                }
                else
                {
                    List<T> set1 = table[1, Hash1(x) % capacity];
                    if (set1.Contains(x))  // remove if contains in second item in x
                    {
                        set1.Remove(x);
                        Interlocked.Decrement(ref currentSize);
                        return true;
                    }
                }
                return false;
            }
            finally
            {
                Release(x);
            }
        }

        public override int Count() => (int)Interlocked.Read(ref currentSize);

        private int Hash0(T i)
        {
            return i.Item1.GetHashCode();
        }

        private int Hash1(T i)
        {
            return i.Item2.GetHashCode();
        }

        public override void Acquire(T x)
        {
            locks[0, Hash0(x) % locks.GetLength(1)].WaitOne();
            locks[1, Hash1(x) % locks.GetLength(1)].WaitOne();
        }

        public override void Release(T x)
        {
            locks[0, Hash0(x) % locks.GetLength(1)].ReleaseMutex();
            locks[1, Hash1(x) % locks.GetLength(1)].ReleaseMutex();
        }

        public override void Resize() // double size table
        {
            int oldCapacity = capacity;
            for (int i = 0; i < locks.GetLength(1); i++)
            {
                locks[0, i].WaitOne();
            }

            try
            {
                if (capacity != oldCapacity) // someone is interfering
                {
                    return;
                }

                Interlocked.Exchange(ref currentSize, 0);

                List<T>[,] oldTable = table; 
                capacity = 2 * capacity; // increase capacity
                table = new List<T>[2, capacity];

                for (int i = 0; i < 2; i++) // create new table with new size
                {
                    for (int j = 0; j < capacity; j++)
                    {
                        table[i, j] = new List<T>(ListSize);
                    }
                }

                for (int i = 0; i < 2; i++) // move each element of the old table
                {
                    for (int j = 0; j < oldCapacity; j++)
                    {
                        foreach (T z in oldTable[i, j])
                        {
                            Add(z);
                        }
                    }
                }
            }
            finally
            {
                for (int i = 0; i < locks.GetLength(1); i++)
                {
                    locks[0, i].ReleaseMutex();
                }
            }
        }

        private bool Relocate(int i, int hi)
        {
            int hj = 0;
            int j = 1 - i;
            for (int round = 0; round < ListSize; round++)
            {
                List<T> iSet = table[i, hi]; // find bucket with hi 
                T y = iSet[0]; // first element in iSet
                switch (i)  // find another hash in iSet
                {
                    case 0:
                        hj = Hash1(y) % capacity;
                        break;
                    case 1:
                        hj = Hash0(y) % capacity;
                        break;
                }
                Acquire(y);
                List<T> jSet = table[j, hj];
                try
                {
                    if (iSet.Remove(y)) // if remove in iSet
                    {
                        if (jSet.Count < ListSize / 2) // if less than half full in jSet - add in jSet
                        {
                            jSet.Add(y);
                            return true;
                        }
                        else if (jSet.Count < ListSize) //if more than half full aand less then full in jSet - add, but change hi,i 
                        {
                            jSet.Add(y);
                            i = 1 - i;
                            hi = hj;
                            j = 1 - j;
                        }
                        else
                        {
                            iSet.Add(y);  // Nothing come out - add in iSet again
                            return false;
                        }
                    }
                    else if (iSet.Count < ListSize / 2) //if less than half full in iSet - add in iSet
                    {
                        return true;
                    }
                }
                finally
                {
                    Release(y);
                }
            }
            return false;
        }
    }
}