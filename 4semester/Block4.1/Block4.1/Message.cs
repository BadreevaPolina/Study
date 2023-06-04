using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Text.Json;

namespace Block4._1
{

    public record Message(MessageType Type, string Sender, string Text = "")
    {
        public byte[] Serialize()
        {
            return JsonSerializer.SerializeToUtf8Bytes(this); //Converts to JSON
        }

        public static Message? Deserialize(byte[] data, int length)
        {
            var json = Encoding.Default.GetString(data, 0, length);
            return JsonSerializer.Deserialize<Message>(json); // Parses the text of the type Message
        }
    }
}
