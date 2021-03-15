package chat;

/**
 * 
 * Properties for logging, i.e. where to log data to, what to log and logging status (i.e. logging on/off)
 * 
 * @author Håkan Strääf
 *
 */

public class LoggingProperties {
	
	// Constants to be used when calling the constructors
	public static final Boolean LOGGING_ON = true;
	public static final Boolean LOGGING_OFF = false;
	public static final Boolean LOG_TO_FILE = true;
	public static final Boolean LOG_TO_SYSTEM_OUT = false;



	private Boolean _logToFile;
	private String _logFileName;
	private Boolean _loggingOngoing;

	/**
	 * Getter method for logToFile
	 * @return True if we are logging to a file, false if we are logging to System.out
	 */
	public Boolean getLogToFile() {
		return _logToFile;
	}

	/**
	 * Getter method for the name of the log file
	 * @return The log file name
	 */
	public String getLogFileName() {
		return _logFileName;
	}

	/**
	 * Getter for the logging status
	 * @return True if logging is on, false otherwise.
	 */
	public Boolean getLoggingOngoing() {
		return _loggingOngoing;
	}

	/**
	 * Setter method for the name of the log file
	 * @param loggingOngoing True if logging should be on, false otherwise.
	 */
	public void setLoggingOngoing(Boolean loggingOngoing) {
		_loggingOngoing = loggingOngoing;
	}
	

	/**
	 * Default constructor to create logging properties using the default values, i.e.
	 * - log to System.out
	 * - logging is on. 
	 */
	public LoggingProperties() {
		_logToFile = LOG_TO_SYSTEM_OUT;
		_logFileName = null;
		_loggingOngoing = LOGGING_ON;
	}
	
	/** 
	 * Constructor using the given logging parameters values
	 * 
	 * @param logToFile True if we are logging to a file, false if we are logging to System.out.
	 * @param logFileName The name of the file to log to, if we are logging to a file.
	 * @param loggingOngoing True if logging is performed, false otherwise.
	 */
	
	public LoggingProperties(Boolean logToFile, String logFileName, Boolean loggingOngoing) {
		_logToFile = logToFile;
		_logFileName = logFileName;
		_loggingOngoing = loggingOngoing;
	}
	
}
