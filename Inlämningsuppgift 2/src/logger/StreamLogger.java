package logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A strategy to log strings to a stream.
 * 
 * @author Håkan Strääf
 *
 */
public class StreamLogger implements LoggingStrategy {

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void logMessage(String message, OutputStream logStream) {
		try {
			Writer logWriter = new PrintWriter(logStream);
			logWriter.write(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " "
					+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
			logWriter.write(" ");
			logWriter.write(message);
			logWriter.write(System.getProperty("line.separator"));
			logWriter.flush();
		} catch (IOException e) {
			/**
			 *  Exceptions during logging are not considered as errors.
			 *  It is assumed that the program execution can continue.
			 */
			
		}
	}
}
