package chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A strategy to always log everything to System.out.
 * 
 * @author Håkan Strääf
 *
 */
public class MandatoryLoggingToSystemOut implements LoggingStrategy {

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void logMessage(String userName, String message) {

		System.out.print(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " "
				+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.print(" ");
		System.out.print(userName);
		System.out.print(" ");
		System.out.println(message);
	}
}
