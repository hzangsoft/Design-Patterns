package logger;

import java.io.OutputStream;


/**
 * Class to handle logging of text strings to an output stream
 * 
 * @author Håkan Strääf
 *
 */

public class Logger  {

	// Constants to be used when calling public methods
	public static final Boolean LOGGING_ON = true;
	public static final Boolean LOGGING_OFF = false;
	
	// The current logging state
	Boolean _loggingState = LOGGING_OFF;
	
	private LoggingStrategy _loggingStrategy;
	private OutputStream _logstream;


	/**
	 * Default constructor, uses the NullLogger strategy, i.e. no logging at all
	 *
	 */
	public Logger() {	
		_loggingStrategy = new NullLogger();
		
	}
	

	/**
	 * Constructor using parameters
	 * @param loggingStrategy The logging strategy to use
	 * @param logStream The stream to log to
	 */
	public Logger (LoggingStrategy loggingStrategy, OutputStream logStream) {
		_loggingStrategy = loggingStrategy;
		_logstream = logStream;
	}
	
	/**
	 * Switching logging on or off depending on the parameter
	 * @param state
	 */
	
	public void setLoggingState(Boolean state)	{
		_loggingState = state;
	}
	
	/**
	 * Log the string in accordance with the logging strategy in use,
	 * if logging is on.
	 * 
	 * @param message The actual string to be logged.
	 * 
	 */
	public void receiveLogMessage(String message){
		if (_loggingState == LOGGING_ON) {
			_loggingStrategy.logMessage(message, _logstream);
		}
	}
	
}
