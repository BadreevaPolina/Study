namespace Block2._2.UnitTests
{
    public class Tests
    { 

        [Test]
        public void ThreadPoolTest()
        {

            ThreadPool pool = new ThreadPool();
            var counter = 10;

            void Decrement(ref int counter)
            {
                counter--;
            }

            for (int j = 0; j < 7; j++)
            {
                pool.Enqueue(() => Decrement(ref counter));
            }

            pool.Dispose();

            Assert.That(counter, Is.EqualTo(3));
        }
    }
}