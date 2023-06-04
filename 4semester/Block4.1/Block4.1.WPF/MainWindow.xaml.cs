using System;
using Block4._1;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Block4._1.WPF
{
    public partial class MainWindow : Window
    {
        private readonly Client Client;
        private readonly ObservableCollection<IPEndPoint> ConnectedPeers = new();
        private readonly ObservableCollection<Message> Message = new();

        public MainWindow()
        {
            InitializeComponent();

            Client = new(new Random().Next(1024, 49151), OnNewMessage);

            Members.ItemsSource = ConnectedPeers;
            Messages.ItemsSource = Message;
            YourAddress.Content = "User " + Client.EndPoint.Port;
        }

        private void OnNewMessage(Message msg)
        {
            Dispatcher.Invoke(delegate
            {
                switch (msg.Type)
                {
                    case MessageType.Text:
                        Message.Add(msg);
                        break;
                    case MessageType.AddPeer:
                        ConnectedPeers.Add(IPEndPoint.Parse(msg.Sender));
                        ShowLeaveButton();
                        break;
                    case MessageType.RemovePeer:
                        ConnectedPeers.Remove(IPEndPoint.Parse(msg.Sender));
                        if (!ConnectedPeers.Any())
                            ShowJoinButton();
                        break;
                }
            });
        }

        private void BorderMouseDown(object sender, MouseButtonEventArgs e)
        {
            if (e.LeftButton == MouseButtonState.Pressed)
                DragMove();
        }


        private void MinimizeButtonClick(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.WindowState = WindowState.Minimized;
        }

        private void WindowStateButtonClick(object sender, RoutedEventArgs e)
        {
            Application.Current.MainWindow.WindowState = Application.Current.MainWindow.WindowState != WindowState.Maximized
                ? WindowState.Maximized
                : WindowState.Normal;
        }


        private void CloseButtonClick(object sender, RoutedEventArgs e)
        {
            Client.Dispose();
            Application.Current.Dispatcher.Invoke(Application.Current.Shutdown);
        }

        private void SendMessageClick(object sender, RoutedEventArgs e)
        {
            Client.SendMessage(MessageBox.Text);
            MessageBox.Clear();
        }

        private void JoinChatClick(object sender, RoutedEventArgs e)
        {
            if (JoinTextBox.Text != "")
            {
                var portToConnect = int.Parse(JoinTextBox.Text.Replace(" ", ""));
                if (portToConnect == Client.EndPoint.Port) return;
                foreach (var peer in ConnectedPeers)
                {
                    if (portToConnect == peer.Port) return;
                }

                Client.Connect(portToConnect);
            }
        }
        private void ShowLeaveButton()
        {
            PortToJoinTextBox.Visibility = Visibility.Collapsed;
            JoinButton.Visibility = Visibility.Collapsed;
            LeaveButton.Visibility = Visibility.Visible;
        }

        private void LeaveChatClick(object sender, RoutedEventArgs e)
        {
            ShowJoinButton();
            Client.Disconnect();
        }

        private void ShowJoinButton()
        {
            PortToJoinTextBox.Visibility = Visibility.Visible;
            JoinButton.Visibility = Visibility.Visible;
            LeaveButton.Visibility = Visibility.Collapsed;
        }

        private void PortTextBoxPreviewInput(object sender, TextCompositionEventArgs e)
        {
            if (!IsTextNumeric(e.Text))
            {
                e.Handled = true;
            }
        }

        private void PortTextBoxPasting(object sender, DataObjectPastingEventArgs e)
        {
            if (e.DataObject.GetDataPresent(typeof(string)))
            {
                string text = (string)e.DataObject.GetData(typeof(string));
                if (!IsTextNumeric(text))
                {
                    e.CancelCommand();
                }
            }
            else
            {
                e.CancelCommand();
            }
        }

        private bool IsTextNumeric(string text)
        {
            Regex regex = new Regex("[^0-9]+");
            return !regex.IsMatch(text);
        }
    }
}
