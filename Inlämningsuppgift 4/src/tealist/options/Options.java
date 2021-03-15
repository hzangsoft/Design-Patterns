package tealist.options;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration for options regarding tealist operations.
 * 
 * @author Thomas Ejnefjäll
 * @version 2011-10-24
 */
public enum Options {
	
	FROM_FILE_FORMAT("-f", 1, new FromFormatOption()),
	TO_FILE_FORMAT("-t", 1, new ToFormatOption()),
	INPUT_FILE("-i", 1, new FromFileNameOption()),
	OUTPUT_FILE("-o", 2, new ToFileNameOption()),
	LIST_FILE_FORMATS("-l", 0, new ShowFormatOption()),
	HELP("-h", 0, new HelpOption()),
	ERROR("!error", 1, new ErrorOption());

	private String value;
	private Integer requiresParameter; // 0 = No, 1=Yes, 2=Optional
	private InterpretOption command;

	/**
	 * Private constructor only for the enumeration itself.
	 * 
	 * @param value Value of the option
	 */
	private Options(String value) {
		this.value = value;
	}	

	/**
	 * Private constructor only for the enumeration itself.
	 * 
	 * @param value Value of the option
	 * @param requiresParamter Indicates if the option has an optional or mandatory parameter 
	 * @param Command The command to be executed when the option occurs
	 */
	private Options(String value, Integer requiresParameter, InterpretOption command) {
		this(value);
		this.requiresParameter = requiresParameter;
		this.command = command;
	}	

	/**
	 * Getter function for the field requiresParameter 
	 * @return the value of requiresParameter
	 */
	public Integer getRequiresParameter() {
		return requiresParameter;
	}

	/**
	 * Getter function for the field command 
	 * @return the value of command
	 */

	public InterpretOption getCommand() {
		return command;
	}


	/**
	 * Getter function for the ERROR option.
	 * 
	 * @return The error option
	 */
	public static Options getErrorOption() {
		return Options.ERROR;
	}
	
	/**
	 * Checks if current option is equal to another option.
	 * 
	 * @param option A string containing the other option
	 * @return true it they are the same option
	 */
	public boolean equals(String option) {
		return value.equalsIgnoreCase(option);
	}
	
	/**
	 * Checks if current option is equal to another option.
	 * 
	 * @param option The other option
	 * @return true it they are the same option
	 */
	public boolean equals(Options option) {
		return this.equals(option.value);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.value;
	}


	/**
	 * Converts a string to the corresponding Enum.
	 * @param optionString The string to translate into an option enum
	 * @return The enum
	 * @throws Exception if the string doesn't match a valid option.
	 */
	public static Options fromString(String optionString) throws Exception{

		Map<String, Options> translateMap = new HashMap<String, Options>();
		translateMap.put("-f", Options.FROM_FILE_FORMAT);
		translateMap.put("-t", Options.TO_FILE_FORMAT);
		translateMap.put("-i", Options.INPUT_FILE);
		translateMap.put("-o", Options.OUTPUT_FILE);
		translateMap.put("-l", Options.LIST_FILE_FORMATS);
		translateMap.put("-h", Options.HELP);

		if (translateMap.containsKey(optionString)) {
			return translateMap.get(optionString);
		} else {
			throw new Exception ( "Invalid option: " + optionString);
		}
		
	}
}