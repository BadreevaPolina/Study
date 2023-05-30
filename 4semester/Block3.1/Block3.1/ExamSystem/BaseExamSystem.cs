using java.nio;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamSystem
{
    public class BaseExamSystem : IExamSystem
    {
        public BaseHashSet<Tuple<long, long>> buffer;

        public int Count => buffer.Count();

        public void Add(long studentId, long courseId) => buffer.Add(new(studentId, courseId));

        public bool Contains(long studentId, long courseId) => buffer.Contains(new(studentId, courseId));

        public void Remove(long studentId, long courseId) => buffer.Remove(new(studentId, courseId));
    }
}
