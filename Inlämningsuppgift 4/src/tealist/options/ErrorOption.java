package tealist.options;

import tealist.fileIO.FileConversionData;

/**
 * Handles and validates the error option.
 * 
 * @author Håkan Strääf
 *
 */
public class ErrorOption implements InterpretOption {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception{
		showError(parameter);
	}

	
	/**
	 * Prints the error to standard out.
	 */
	private void showError(String errorMessage)
	{
		System.out.println("Error: " + errorMessage);
	}
}
