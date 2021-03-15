package tealist;


import java.util.Map;

import tealist.fileIO.FileConversionData;
import tealist.fileIO.FileFormats;
import tealist.options.OptionParser;
import tealist.options.Options;

/**
 * Handles parsing of options and conversion between different files 
 * containing information about tea.
 * 
 * @author Thomas Ejnefjäll
 */
public class TeaList 
{	
	/**
	 * Constructs a TeaList
	 */
	public TeaList() {

	}

	/**
	 * Process a request containing one or more options. For information
	 * on valid options see the readme file.
	 * 
	 * @param args Options
	 */
	public void processRequest(String[] args)
	{
		// Create container for file conversion parameters
		FileConversionData fileConverter = new FileConversionData();
		
		// Validate all options and perform file conversion if so requested. 
		try {
			validateRequest(args, fileConverter);
			if (fileConverter.conversionRequested()) {
					FileFormats.convert(fileConverter);
			}
		} catch (Exception e) {
			this.showError(e.getMessage());
		}
	}

	
	/**
	 * Validate if all the options given on the command line are valid and perform
	 * conversion if requested and all required parameters are present. 
	 * @param args The options to be validated
	 * @param fileConverter The file converter data to be used
	 * @throws Exception if there is any error with any of the options
	 */
	private void validateRequest(String[] args, FileConversionData fileConverter) throws Exception{
		OptionParser optionHandler = new OptionParser();		
		// Parse the options into a map with the options and their parameter values (when applicable)
		Map<Options, String> request = optionHandler.parseRequest(args);

		// Iterate over all the options and the map and execute the associated command.
		// Data for the conversion is stored in the fileConverter object. 

		for(Options param : request.keySet())
		{
			param.getCommand().interpret(request.get(param), fileConverter);
		}

		if (fileConverter.conversionRequested()) {
			String result = fileConverter.validate();
			if (result != null) {
				throw new Exception(result);
			}
		}
	}

	/**
	 * Prints any errors to standard out.
	 * 
	 * @param error The error that happened
	 */
	private void showError(String error)
	{
		System.out.println("Error: " + error);
		try {
			Options.HELP.getCommand().interpret(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}