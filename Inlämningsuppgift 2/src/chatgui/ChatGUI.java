package chatgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import logger.Logger;
import logger.StreamLogger;
import observerinterfaces.MessageListener;
import observerinterfaces.MessageProvider;


/**
 * The main GUI for a simple chat application. 
 * 
 * @author Thomas Ejnefjäll
 */
public class ChatGUI extends JFrame implements MessageListener, MessageProvider{

	private static final long serialVersionUID = -6901406569465760897L;
	private JTextArea _chatArea, _messageArea;
	private JButton _sendButton;
	private JButton _loggingOnButton;
	private JButton _loggingOffButton;
	private String _user;
	
	// HS: Create a chat logger and an error logger using the StreamLogger strategy 
	private Logger _chatLogger = new Logger(new StreamLogger(), System.out);
	private Logger _errorLogger = new Logger(new StreamLogger(), System.err);
	
	//HS: A list to store the listeners
	private List<MessageListener> _chatListeners = new ArrayList<MessageListener>(); 

	/**
	 * Creates a ChatGUI
	 * 
	 * @param userName the name to use in the chat
	 */
	public ChatGUI(String userName) {
		this.setTitle("Simple Chat - " + userName);
		_user = userName;
		this.initializeGUI();
		this.initializeLogging();
		this.addGUIListeners();		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Initialises the logging.
	 * 
	 * Creates two loggers, one for logging of chat communication and one for 
	 * error logging.
	 *  
	 */
	private void initializeLogging() {
		_chatLogger.setLoggingState(Logger.LOGGING_ON);
		_errorLogger.setLoggingState(Logger.LOGGING_ON);
	}
	
	/**
	 * Initialises the GUI
	 */
	private void initializeGUI() {
		_chatArea = new JTextArea(25, 1);
		_messageArea = new JTextArea(3, 10);
		_sendButton = new JButton("Send");
		
		//HS: Add two buttons for turning logging on and off
		//No time has been spent on improving the GUI. 
		_loggingOnButton = new JButton("Turn chat logging on");
		_loggingOffButton = new JButton("Turn chat logging off");

		_messageArea.setLineWrap(true);
		_messageArea.setBorder(BorderFactory.createLineBorder(Color.black));
		_chatArea.setEnabled(false);
		_chatArea.setLineWrap(true);

		Container contentPane = this.getContentPane();

		contentPane.add(_chatArea, BorderLayout.NORTH);
		contentPane.add(_messageArea, BorderLayout.WEST);
		contentPane.add(_sendButton, BorderLayout.CENTER);
		contentPane.add(_loggingOnButton, BorderLayout.EAST);
		contentPane.add(_loggingOffButton, BorderLayout.SOUTH);

		// HS: Modified the horizontal size to be able to see all the text in the title.
		this.setSize(500, 500);
		this.setResizable(false);
	
	}
		
	/**
	 * Adds GUI related listeners
	 */
	private void addGUIListeners() {
		_sendButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}});
		
		// Add action listeners for the logging on/off buttons.
		_loggingOnButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				_chatLogger.setLoggingState(Logger.LOGGING_ON);
			}});
		_loggingOffButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				_chatLogger.setLoggingState(Logger.LOGGING_OFF);
			}});
		
		_messageArea.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (_messageArea.getText().length() > 0) {
						_messageArea.setText(_messageArea.getText().substring(0, _messageArea.getText().length() - 1));
						sendMessage();
					}
				}
			}});		
	}
	/**
	 * Send current message to all users
	 */
	private void sendMessage() {

		String message = _user + " : " + _messageArea.getText();
		updateGUIChatListeners(message);

		_chatLogger.receiveLogMessage(message);

		_messageArea.setText("");
		_messageArea.grabFocus();			

	}


	/**
	 * Informs the user that an error has occurred and exits the application
	 */
	public void error(String errorMessage) {		
		JOptionPane.showMessageDialog(this, "An error has occured and the application will close", "Error", JOptionPane.WARNING_MESSAGE);
		
		_errorLogger.receiveLogMessage(errorMessage);

		this.setVisible(false);
		this.dispose();
		System.exit(ERROR);		
	}

	
	/**
	 * Receives message from all users 
	 * 
	 * @param message The received message
	 */
	@Override
	public void receiveChatMessage(String message) {
		_chatArea.append(message + "\n");		
	}
	
	/**
	 * Update all listeners with the received message.
	 * @param message The received message
	 */
	
	private void updateGUIChatListeners(String message) {
		for (final MessageListener listener : _chatListeners) {
			try {
				listener.receiveChatMessage(message);
			} catch (IOException e) {
				this.error("Error occurred during chat communication");
			}
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addMessageListener(MessageListener listener) {
		return _chatListeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeMessageListener(MessageListener listener) {
		return _chatListeners.remove(listener);
	}



}