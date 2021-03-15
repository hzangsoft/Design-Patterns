package logger;


import java.io.OutputStream;

/**
 * A logging strategy which does absolutely nothing.
 * 
 * @author Håkan Strääf
 *
 */
public class NullLogger implements LoggingStrategy {

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void logMessage(String message, OutputStream logStream) {

	}

}
