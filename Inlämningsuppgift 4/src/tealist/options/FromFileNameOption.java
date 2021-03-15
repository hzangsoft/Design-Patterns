package tealist.options;

import tealist.fileIO.FileConversionData;
/**
 * Handles and validates the input file name option.
 * 
 * @author Håkan Strääf
 *
 */
public class FromFileNameOption implements InterpretOption {

	/**
	 * Constructor for the class.
	 */
	public FromFileNameOption() {
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception {
		if (parameter == null) {
			throw new Exception("-i must be followed by the name of the input file");
		} else {
			fileConverter.setInFileName(parameter);
		} 
	}

}
