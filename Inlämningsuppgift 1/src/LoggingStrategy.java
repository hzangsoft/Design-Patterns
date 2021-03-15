package chat;

/**
 * A strategy for logging in the chat tool
 * @author Håkan Strääf 
 *
 */


public interface LoggingStrategy {
	/**
	 * Perform the logging in the chat tool
	 * @param userName The user name of the sender of the message.
	 * @param message The actual message sent.
	 */
	public void logMessage(String userName, String message);

}
