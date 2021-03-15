package tealist.options;

import tealist.fileIO.FileConversionData;

/**
 * Handles and validates the output file name option.
 * 
 * @author Håkan Strääf
 *
 */
public class ToFileNameOption implements InterpretOption {

	/**
	 * Constructor for the class.
	 */
	public ToFileNameOption() {
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception {
			fileConverter.setOutFileName(parameter);
	}
}
