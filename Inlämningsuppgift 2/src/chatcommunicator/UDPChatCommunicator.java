package chatcommunicator;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import java.util.ArrayList;
import java.util.List;

import observerinterfaces.MessageProvider;
import observerinterfaces.MessageListener;

/**
 * The communicator handles the network traffic between all chat clients.
 * Messages are sent and received via the UDP protocol which may lead to
 * messages being lost.
 * 
 * @author Thomas Ejnefjäll
 */
public class UDPChatCommunicator implements Runnable, MessageListener, MessageProvider {
	private final int DATAGRAM_LENGTH = 100;
	private final int PORT = 6789;
	private final String MULTICAST_ADDRESS = "228.28.28.28";

	private List<MessageListener> _listeners = new ArrayList<MessageListener>(); 
	
	/**
	 * Creates a ChatCommunicator
	 * 
	 */
	public UDPChatCommunicator() {

		/*
		 * force java to use IPv4 so we do not get a problem when using IPv4
		 * multicast address
		 */
		System.setProperty("java.net.preferIPv4Stack", "true");
	}

	/**
	 * Sends the chat message to all clients
	 * 
	 * @param message
	 *            Text message to send
	 * @throws IOException 
	 * @throws IOException
	 *             If there is an IO error
	 */
	public void sendChat(String message) throws IOException  {

		DatagramSocket socket = new DatagramSocket();

		byte[] b = message.getBytes();

		DatagramPacket datagram = new DatagramPacket(b, b.length, InetAddress.getByName(MULTICAST_ADDRESS), PORT);

		socket.send(datagram);
		socket.close();
		
		// HS: Uncomment the next line to test error handling
//		throw new IOException("Testar");
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
				updateListeners(message);

				datagram.setLength(b.length);
			}
		}
	}

	@Override
	public void run() {
		try {
			this.listenForMessages();
		} catch (IOException e) {
			 // The error is not handled, and it is propagated to the gui.
		}
	}
	

	/**
	 * Receives message from all users 
	 * 
	 * @param message The received message
	 * @throws IOException When IO exception occurs. The error is not handled, and it is propagated to the gui.
	 * 
	 */
	@Override
	public void receiveChatMessage(String message) throws IOException{
			sendChat(message);
	}
	
	
	
	/**
	 * Update all listeners with the message received from the other chat users.
	 * Update all listeners with the received message.
	 * @param message The received message
	 * @throws IOException When IO exception occurs. The error is not handled, and it is propagated to the gui.
	 */
	
	private void updateListeners(String message) throws IOException {
		for (final MessageListener listener : _listeners) {
			listener.receiveChatMessage(message);
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addMessageListener(MessageListener listener) {
		return _listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeMessageListener(MessageListener listener) {
		return _listeners.remove(listener);
	}
}