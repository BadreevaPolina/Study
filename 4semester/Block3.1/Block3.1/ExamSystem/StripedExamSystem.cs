

namespace ExamSystem
{
    public class StripedExamSystem : BaseExamSystem
    {
        public StripedExamSystem()
        {
            buffer = new StripedHashSet<Tuple<long, long>>(30);
        }

        public StripedExamSystem(int capacity)
        {
            buffer = new StripedHashSet<Tuple<long, long>>(capacity);
        }

    }
}