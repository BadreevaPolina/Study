using NUnit.Framework;

namespace Block2._1.UnitTests
{
    public class Tests
    {

        [Test]
        public void ConsumerTest()
        {
            List<int> buffer = new List<int>() { 56, 58 };
            const int countConsumer = 2;

            Consumer consumer = new Consumer(countConsumer, buffer);
            consumer.Run();
            consumer.Stop();
                      
            Assert.That(buffer.Count, Is.EqualTo(0));
        }

        [Test]
        public void ProducerTest()
        {
            List<int> buffer = new List<int>();
            const int countProducer = 1;

            Producer producer = new Producer(countProducer, buffer);
            producer.Run();
            producer.Stop();

            Assert.That(buffer.Count, Is.EqualTo(1));
        }

    }
}