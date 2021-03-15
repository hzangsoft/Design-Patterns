package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * The communicator handles the network traffic between all chat clients.
 * Messages are sent and received via the UDP protocol which may lead to
 * messages being lost.
 * 
 * @author Thomas Ejnefjäll
 */
public class UDPChatCommunicator implements Runnable {
	private final int DATAGRAM_LENGTH = 100;
	private final int PORT = 6789;
	private final String MULTICAST_ADDRESS = "228.28.28.28";
	private ChatGUI _chat = null;

	// HS: Create a logger using a strategy to always log to System.out 
	private Logger mandatoryLogger = new Logger(new LoggingProperties(LoggingProperties.LOG_TO_SYSTEM_OUT, null, LoggingProperties.LOGGING_ON));

	
	/**
	 * Creates a ChatCommunicator
	 * 
	 * @param chat
	 *            the GUI that want to receive incoming messages
	 */
	public UDPChatCommunicator(ChatGUI chat) {
		_chat = chat;
		/*
		 * force java to use IPv4 so we do not get a problem when using IPv4
		 * multicast address
		 */
		System.setProperty("java.net.preferIPv4Stack", "true");

	}

	/**
	 * Sends the chat message to all clients
	 * 
	 * @param sender
	 *            Name of the sender
	 * @param message
	 *            Text message to send
	 * @throws IOException
	 *             If there is an IO error
	 */
	public void sendChat(String sender, String message) throws IOException {

		DatagramSocket socket = new DatagramSocket();
		String toSend = sender + ": " + message;
		byte[] b = toSend.getBytes();

		DatagramPacket datagram = new DatagramPacket(b, b.length, InetAddress.getByName(MULTICAST_ADDRESS), PORT);

		// HS: Log the message to be sent
		mandatoryLogger.logMessage(sender, message);
	

		socket.send(datagram);
		socket.close();
	}

	/**
	 * Starts to listen for messages from other clients
	 */
	public void startListen() {
		new Thread(this).start();
	}

	/**
	 * Listens for messages from other clients
	 * 
	 * @throws IOException
	 *             If there is an IO error
	 */
	private void listenForMessages() throws IOException {
		byte[] b = new byte[DATAGRAM_LENGTH];
		DatagramPacket datagram = new DatagramPacket(b, b.length);

		try (MulticastSocket socket = new MulticastSocket(PORT)) {
			socket.joinGroup(InetAddress.getByName(MULTICAST_ADDRESS));

			while (true) {
				socket.receive(datagram);
				String message = new String(datagram.getData());
				message = message.substring(0, datagram.getLength());
				_chat.receiveMessage(message);

				datagram.setLength(b.length);
			}
		}
	}

	@Override
	public void run() {
		try {
			this.listenForMessages();
		} catch (IOException e) {
			_chat.error();
		}
	}
}