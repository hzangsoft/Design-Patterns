package logger;

import java.io.OutputStream;

/**
 * A strategy for logging strings
 * @author Håkan Strääf 
 *
 */


public interface LoggingStrategy {
	/**
	 * Perform logging of strings
	 * @param message The string to be logged.
	 * @param logStream The stream to log the string to.
	 */
	public void logMessage(String message, OutputStream logStream);

}
