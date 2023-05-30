namespace ExamSystem
{
    public class StripedHashSet<T> : BaseHashSet<T> where T : Tuple<long, long>
    {
        private readonly ReaderWriterLockSlim[] locks; // lock in each element 

        public StripedHashSet(int capacity) : base(capacity)
        {
        }

        private bool PolicyDemandsResize => currentSize / table.Length > 4; // 4 elements in each list
        

        public override void Resize() // double size table
        {
            int oldCapacity = table.Length;

            foreach (var l in locks) // enter all write lock
            {
                l.EnterWriteLock();
            }

            try
            {
                if (oldCapacity != table.Length) // someone is interfering
                {
                    return;
                }
                int newCapacity = 2 * oldCapacity; // increase capacity
                List<T>[] oldTable = table;
                table = new List<T>[newCapacity];
                for (int i = 0; i < newCapacity; i++) // create new table with new size
                    table[i] = new List<T>();
                foreach (List<T> bucket in oldTable)
                {
                    foreach (T x in bucket) // move each element of the old table
                    {
                        table[x.GetHashCode() % table.Length].Add(x);
                    }
                }
            }
            finally
            {
                foreach (var l in locks) // exit all write lock
                {
                    l.ExitWriteLock();
                }
            }
        }

        public override bool Contains(T x)
        {
            Acquire(x);  // enter read block in bucket
            try
            {
                int bucket = x.GetHashCode() % table.Length; // find bucket
                return table[bucket].Contains(x); // return result
            }
            finally
            {
                Release(x);
            }
        }

        public override bool Add(T x)
        {
            var result = false;
            Acquire(x);
            try
            {
                int bucket = x.GetHashCode() % table.Length; // find bucket
                if (!table[bucket].Contains(x)) // if there is no this element - add
                {
                    table[bucket].Add(x);
                    result = true;
                    currentSize++;
                }
            }
            finally
            {
                Release(x);
            }
            if (PolicyDemandsResize)
                Resize();
            return result;
        }

        public override bool Remove(T x)
        {
            Acquire(x);
            try
            {
                int bucket = x.GetHashCode() % table.Length; // find bucket
                bool result = table[bucket].Remove(x); // remove element if any
                currentSize = result ? currentSize - 1 : currentSize;
                return result;
            }
            finally
            {
                Release(x);
            }
        }

        public override void Acquire(T x)
        {
            locks[x.GetHashCode() % locks.Length].EnterWriteLock();
        }

        public override void Release(T x)
        {
            locks[x.GetHashCode() % locks.Length].ExitWriteLock();
        }

        public override int Count() => currentSize;
    }
}