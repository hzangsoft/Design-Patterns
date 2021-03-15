package tealist.options;

import tealist.fileIO.FileConversionData;
import tealist.fileIO.FileFormats;

/**
 * Handles and validates the input file format option.
 * 
 * @author Håkan Strääf
 *
 */
public class FromFormatOption implements InterpretOption {

	
	/**
	 * Constructor for the class.
	 */
	public FromFormatOption() {
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception {
		try {
			fileConverter.setInFileFormat(FileFormats.valueOf(parameter.toUpperCase()));

		} catch (IllegalArgumentException e) {
			throw new Exception("-f must be followed by a valid file format");
		}
	}

}
