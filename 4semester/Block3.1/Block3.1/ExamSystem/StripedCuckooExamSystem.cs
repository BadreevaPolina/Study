namespace ExamSystem
{
    public class StripedCuckooExamSystem : BaseExamSystem
    {

        public StripedCuckooExamSystem()
        {
            buffer = new StripedCuckooHashSet<Tuple<long, long>>(30);
        }

        public StripedCuckooExamSystem(int capacity)
        {
            buffer = new StripedCuckooHashSet<Tuple<long, long>>(capacity);
        }
    }
}
