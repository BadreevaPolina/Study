using System.Net;
using System.Net.Sockets;


namespace Block4._1
{
    public class Client
    {
        private static readonly IPAddress LocalHost = IPAddress.Parse("127.0.0.1");
        public IPEndPoint EndPoint { get; }
        private readonly Action<Message> OnNewMessage;

        private readonly List<IPEndPoint> ConnectedPeers = new();
        private readonly Socket Socket = new(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
        private readonly Thread Thread;

        private volatile bool Disposed;

        public Client(int port, Action<Message> onNewMessageCallback)
        {
            EndPoint = new IPEndPoint(LocalHost, port);
            OnNewMessage = onNewMessageCallback;

            Socket.Bind(EndPoint);  // EndPoint to be associated with the receiving socket.
            Thread = new Thread(Run);
            Thread.Start();
        }

        public void Dispose()
        {
            if (Disposed) return;
            Disposed = true;

            ConnectedPeers.ForEach(peer => Socket.SendTo(
                new Message(MessageType.RemovePeer, EndPoint.ToString()).Serialize(),
                peer)); // Sends remove data to the specified endpoint in all peers
            Socket.Shutdown(SocketShutdown.Both); // Blocks the transmission and receipt of data
            Socket.Close(); //Close socket
            Thread.Join();
        }

        public void Connect(int port)
        {
            var endPoint = new IPEndPoint(LocalHost, port);
            var addMsg = new Message(MessageType.AddPeer, endPoint.ToString());
            var connectMsg = new Message(MessageType.Connect, EndPoint.ToString());

            ConnectedPeers.Add(endPoint);
            OnNewMessage(addMsg);
            Socket.SendTo(connectMsg.Serialize(), endPoint);
        }

        public void Disconnect()
        {
            var msg = new Message(MessageType.RemovePeer, EndPoint.ToString());
            var serialized = msg.Serialize();

            foreach (var peer in ConnectedPeers.ToList()) // Sends remove data to the specified endpoint in all peers
            {
                Socket.SendTo(serialized, peer);
                OnNewMessage(msg with { Sender = peer.ToString() });
            }
            ConnectedPeers.Clear(); // Delete all peers
        }

        public void SendMessage(string message)
        {
            var msg = new Message(MessageType.Text, EndPoint.ToString(), message);
            var serialized = msg.Serialize();

            OnNewMessage(msg);
            ConnectedPeers.ForEach(peer => Socket.SendTo(serialized, peer)); // Sends data to the specified endpoint in all peers
        }

        private void Run()
        {
            while (true)
            {
                if (Disposed) return;

                var buffer = new byte[1024];
                int size;

                try
                {
                    size = Socket.Receive(buffer); // Receive data
                }
                catch (SocketException)
                {
                    continue;
                }

                var msg = Message.Deserialize(buffer, size);
                if (msg == null) continue;

                switch (msg.Type) // process the message
                {
                    case MessageType.Connect:
                        var addMsg = new Message(MessageType.AddPeer, msg.Sender); 
                        foreach (var peer in ConnectedPeers) // Sends data to the specified endpoint in all peers
                        {
                            Socket.SendTo(addMsg.Serialize(), peer);
                            Socket.SendTo(
                                (addMsg with { Sender = peer.ToString() }).Serialize(),
                                ParseEndPoint(addMsg.Sender)
                                );
                        }
                        OnNewMessage(addMsg);
                        ConnectedPeers.Add(ParseEndPoint(addMsg.Sender)); // Add in peers
                        break;
                    case MessageType.AddPeer:
                        OnNewMessage(msg);
                        ConnectedPeers.Add(ParseEndPoint(msg.Sender)); // Add in peers
                        break;
                    case MessageType.RemovePeer:
                        OnNewMessage(msg);
                        ConnectedPeers.Remove(ParseEndPoint(msg.Sender)); // Remove out of peers
                        break;
                    case MessageType.Text:
                        OnNewMessage(msg); // New message
                        break;
                    default:
                        throw new ArgumentOutOfRangeException();
                }
            }
        }

        private IPEndPoint ParseEndPoint(string str) // Creates a new IPEndPoint with the specified address and port.
        {
            var endPoint = str.Split(":");
            return new IPEndPoint(IPAddress.Parse(endPoint[0]), int.Parse(endPoint[1]));
        }
    }
}