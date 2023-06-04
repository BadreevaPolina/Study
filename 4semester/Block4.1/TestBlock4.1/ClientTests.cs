using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities.ObjectModel;
using Microsoft.VisualStudio.TestPlatform.CommunicationUtilities;
using System.Net;
using Block4._1;

namespace TestBlock4._1
{

    public class ClientTests
    {
        private Client Client1;
        private Client Client2;
        private Client Client3;
        private List<IPEndPoint> ConnectedPeers1;
        private List<Block4._1.Message> Messages1;
        private List<IPEndPoint> ConnectedPeers2;
        private List<Block4._1.Message> Messages2;
        private List<IPEndPoint> _connectedPeers3;
        private List<Block4._1.Message> Messages3;

        private void OnNewMessage(Block4._1.Message msg, List<IPEndPoint> connectedPeers, List<Block4._1.Message> messages)
        {
            switch (msg.Type)
            {
                case Block4._1.MessageType.Text:
                    messages.Add(msg);
                    break;
                case Block4._1.MessageType.AddPeer:
                    connectedPeers.Add(IPEndPoint.Parse(msg.Sender));
                    break;
                case Block4._1.MessageType.RemovePeer:
                    connectedPeers.Remove(IPEndPoint.Parse(msg.Sender));
                    break;
            }
        }

        private void OnNewMessage1(Block4._1.Message msg)
        {
            OnNewMessage(msg, ConnectedPeers1, Messages1);
        }

        private void OnNewMessage2(Block4._1.Message msg)
        {
            OnNewMessage(msg, ConnectedPeers2, Messages2);
        }

        private void OnNewMessage3(Block4._1.Message msg)
        {
            OnNewMessage(msg, _connectedPeers3, Messages3);
        }

        [SetUp]
        public void Setup()
        {
            ConnectedPeers1 = new();
            Messages1 = new();
            ConnectedPeers2 = new();
            Messages2 = new();
            _connectedPeers3 = new();
            Messages3 = new();
            Client1 = new Client(1111, OnNewMessage1);
            Client2 = new Client(2222, OnNewMessage2);
            Client3 = new Client(3333, OnNewMessage3);
        }

        [TearDown]
        public void Teardown()
        {
            Client1.Dispose();
            Client2.Dispose();
            Client3.Dispose();
        }

        [Test]
        public void TwoClientsJoinTest()
        {
            Client1.Connect(2222);
            Thread.Sleep(100);

            Assert.That(ConnectedPeers1.Count, Is.EqualTo(1));
            Assert.That(ConnectedPeers2.Count, Is.EqualTo(1));
            Assert.That(ConnectedPeers2[0], Is.EqualTo(Client1.EndPoint));
            Assert.That(ConnectedPeers1[0], Is.EqualTo(Client2.EndPoint));
        }

        [Test]
        public void ThreeClientsJoinTest()
        {
            Client1.Connect(2222);
            Client3.Connect(1111);
            Thread.Sleep(100);

            Assert.That(ConnectedPeers1.Count, Is.EqualTo(2));
            Assert.That(ConnectedPeers2.Count, Is.EqualTo(2));
            Assert.That(ConnectedPeers2.Count, Is.EqualTo(2));
        }

        [Test]
        public void SendMessageTest()
        {
            Client1.Connect(2222);
            Client3.Connect(1111);
            Client2.SendMessage("Hello");
            Thread.Sleep(100);

            Assert.That(Messages1.Count, Is.EqualTo(1));
            Assert.That(Messages2.Count, Is.EqualTo(1));
            Assert.That(Messages3.Count, Is.EqualTo(1));
            var msg = new Block4._1.Message(Block4._1.MessageType.Text, Client2.EndPoint.ToString(), "Hello");
            Assert.That(Messages1[0].Type, Is.EqualTo(msg.Type));
            Assert.That(Messages1[0].Sender, Is.EqualTo(msg.Sender));
            Assert.That(Messages1[0].Text, Is.EqualTo(msg.Text));
        }

        [Test]
        public void DisconnectTest()
        {
            Client1.Connect(2222);
            Client3.Connect(1111);
            Thread.Sleep(100);
            Client2.Disconnect();
            Thread.Sleep(100);

            Assert.That(ConnectedPeers2.Count, Is.EqualTo(0));
            Assert.That(ConnectedPeers1.Count, Is.EqualTo(1));
            Assert.That(_connectedPeers3.Count, Is.EqualTo(1));
            Assert.That(_connectedPeers3[0], Is.EqualTo(Client1.EndPoint));
            Assert.That(ConnectedPeers1[0], Is.EqualTo(Client3.EndPoint));
        }
    }
}