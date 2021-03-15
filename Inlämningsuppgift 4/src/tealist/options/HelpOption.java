package tealist.options;

import tealist.fileIO.FileConversionData;

/**
 * Handles and validates the help option.
 * 
 * @author Håkan Strääf
 *
 */
public class HelpOption implements InterpretOption {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void interpret(String parameter, FileConversionData fileConverter) throws Exception{
		showHelp();
	}

	
	/**
	 * Prints the help to standard out.
	 */
	private void showHelp()
	{
		System.out.println("Usage: java -jar TeaList.jar [OPTION][VALUE]");
		System.out.println("Options can be given in any order");
		System.out.println("");
		System.out.println("Input/output format");
		System.out.println("-f from file format (requires value after)");
		System.out.println("-t to file format (require value after)");
		System.out.println("");
		System.out.println("Input/output file");
		System.out.println("-i input file (requires value after)");
		System.out.println("-o output file (if no value is given standard output will be used)");
		System.out.println("");
		System.out.println("Information");
		System.out.println("-l list avaliable file formats");
		System.out.println("-h print help");
		System.out.println("");
		System.out.println("Example of valid options and values");
		System.out.println("java -jar TeaList.jar -h");
		System.out.println("(prints help)");
		System.out.println("java -jar TeaList.jar -f text -t xml -i tea.txt");
		System.out.println("(reads a tealist in text format in tea.txt and writes it as xml to standard output)");
		System.out.println("java -jar TeaList.jar -o tea.txt -i tea.xml -t txt -f xml");
		System.out.println("(reads a tealist in xml format in tea.xml and writes it as text to tea.txt)");		
	}
}
