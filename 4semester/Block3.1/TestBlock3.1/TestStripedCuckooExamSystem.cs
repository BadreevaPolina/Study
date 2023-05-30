using NUnit.Framework;
using ExamSystem;
namespace TestBlock3._1
{
    public class StripedCuckooExamSystemTests
    {
        [Test]
        public void AddTest()
        {
            var examSystem = new StripedCuckooExamSystem();
            examSystem.Add(1, 3);
            examSystem.Add(35, 4);
            examSystem.Add(167, 6565);

            Assert.That(examSystem.Count, Is.EqualTo(3));
        }

        [Test]
        public void ContainsTest()
        {
            var examSystem = new StripedCuckooExamSystem();
            examSystem.Add(1, 3);
            examSystem.Add(35, 4);
            examSystem.Add(167, 6565);

            Assert.IsTrue(examSystem.Contains(1, 3));
            Assert.IsFalse(examSystem.Contains(167, 6564));
        }

        [Test]
        public void RemoveTest()
        {
            var examSystem = new StripedCuckooExamSystem();

            examSystem.Add(1, 3);
            examSystem.Add(35, 4);
            examSystem.Add(167, 6565);

            examSystem.Remove(35, 4);
            examSystem.Remove(167, 6565);

            Assert.That(examSystem.Count, Is.EqualTo(1));
        }


        [Test]
        public void CountTest()
        {
            var examSystem = new StripedCuckooExamSystem();

            for (int i = 0; i < 100; i++)
            {
                examSystem.Add(15 * i, 30 * i);
            }

            Assert.That(examSystem.Count, Is.EqualTo(100));
        }
    }
    }