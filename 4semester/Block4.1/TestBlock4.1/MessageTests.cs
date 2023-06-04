using Block4._1;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TestBlock4._1
{
    public class MessageTests
    {
        [Test]
        public void SerializeDeserializeTest()
        {
            var msg = new Message(MessageType.Text, "127.0.0.1:3000", "Hello");
            var serialized = msg.Serialize();
            var deserialized = Message.Deserialize(serialized, serialized.Length);

            Assert.That(deserialized.Type, Is.EqualTo(msg.Type));
            Assert.That(deserialized.Sender, Is.EqualTo(msg.Sender));
            Assert.That(deserialized.Text, Is.EqualTo(msg.Text));
        }
    }
}
