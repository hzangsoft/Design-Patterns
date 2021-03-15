package tealist.options;

import tealist.fileIO.FileConversionData;
import tealist.fileIO.FileFormats;

/**
 * Handles and validates the output file format option.
 * 
 * @author Håkan Strääf
 *
 */
public class ToFormatOption implements InterpretOption {

	/**
	 * Constructor for the class.
	 */
	public ToFormatOption() {
	}

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception {
		try {
			fileConverter.setOutFileFormat(FileFormats.valueOf(parameter.toUpperCase()));
		} catch (IllegalArgumentException e) {
			throw new Exception("-t must be followed by a valid file format");
		}
	}

}
