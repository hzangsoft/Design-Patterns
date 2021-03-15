package tealist.options;

import tealist.fileIO.FileConversionData;

/**
 * Interface for executing the action for options given to the application,
 * 
 * @author Håkan Strääf
 *
 */


public interface InterpretOption {
	/**
	 * Method to interpret a single option.
	 * 
	 * @param parameter Possible parameter for the option. Null if not present.
	 * @param fileConversionData Object to save option parameters. Not used for all options
	 * @throws Exception if there is something wrong with the option parameter 
	 */
	public void interpret(String parameter, FileConversionData fileConversionData) throws Exception;

}

