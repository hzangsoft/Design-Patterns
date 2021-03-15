package tealist.options;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses the argument string and validates the result
 * 
 * @author Håkan Strääf
 *
 */
public class OptionParser {
	
	/**
	 * Constructs an OptionParser
	 */
	public OptionParser() {
	}

	
	/**
	 * Parse the request. 
	 * 
	 * @param args The options given to the program
	 * @return a map with the options and their parameter values when applicable
	 * @throws Exception when there is something wrong with any of the options
	 */
	public Map<Options, String> parseRequest(String[] args) throws Exception 
	{
		Map<Options, String> request = new HashMap<Options, String>();

		for(int i = 0; i < args.length; i ++) 
		{
			// Get option as the internal Enum.
			Options opt = Options.fromString(args[i]);
			String parameter = null;

			// CHeck if the option should have a parameter
			if (opt.getRequiresParameter() == 0)
			{
				// No parameter
				parameter = null;	
			}	
			else if (opt.getRequiresParameter() == 1)
			{
				// The option requires a parameter 
				if (args.length > i + 1) {
					// Another argument exists. Grab it.
					parameter = args[++i];				
				}
				else 
				{
					// Another argument doesn't exist. Enter null.
					parameter = null;	
				}
			}
			else if (opt.getRequiresParameter() == 2) {
				// The option may have an extra parameter 
				if ((args.length > i + 1) && (!args[i + 1].startsWith("-"))) {
					// Another argument exists and that argument is not an option. Grab it.
					parameter = args[++i];				
				} else {
					// Enter null as parameter 
					parameter = null;	
				}
			}

			// Check if the option already exists
			if (request.containsKey(opt)) {
				throw new Exception("Each option can only be used once.");
			} else {
				request.put(opt, parameter);
			}
		}
		if (request.isEmpty())        {
			request.put(Options.getErrorOption(), "No options given");
		}
		return request;
	}
}

