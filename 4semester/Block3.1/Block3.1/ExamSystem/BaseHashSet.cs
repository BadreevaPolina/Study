using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamSystem
{
    public abstract class BaseHashSet<T> where T : Tuple<long, long>
    {
        public List<T>[] table;
        public int currentSize;
        public BaseHashSet(int capacity)
        {
            currentSize = 0;
            table = new List<T>[capacity];
            for (var i = 0; i < capacity; i++)
            {
                table[i] = new List<T>();
            }
        }

        public abstract void Resize();
        public abstract void Acquire(T x);
        public abstract void Release(T x);

        public abstract bool Contains(T x);
        public abstract bool Add(T x);
        public abstract bool Remove(T x);
        public abstract int Count();

    }
}
