namespace Block2._1
{
    internal class Program
    {
        private static List<int> buffer = new List<int>();

        static void Main(string[] args)
        {
            const int countProducer = 4;
            const int countConsumer = 4;

            Consumer consumer = new Consumer(countConsumer, buffer);
            Producer producer = new Producer(countProducer, buffer);

            producer.Run();
            consumer.Run();         

            Console.ReadKey(true);

            producer.Stop();
            consumer.Stop();

        }

    }
}
