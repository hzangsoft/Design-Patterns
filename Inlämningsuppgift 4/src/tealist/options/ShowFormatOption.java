package tealist.options;

import tealist.fileIO.FileConversionData;
import tealist.fileIO.FileFormats;


/**
 * Handles and validates the list format option.
 * 
 * @author Håkan Strääf
 *
 */
public class ShowFormatOption implements InterpretOption {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception{
		showFileFormats();
	}

	/**
	 * Prints valid file formats to standard out.
	 */
	private void showFileFormats()
	{
		System.out.println("TeaList 0.2 supports the following file formats");
		FileFormats.showFileFormats();
	}
	
}
