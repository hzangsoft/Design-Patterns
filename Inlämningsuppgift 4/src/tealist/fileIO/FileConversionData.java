package tealist.fileIO;
/**
 * Handles the data needed for the file conversion.
 * @author Håkan Strääf
 *
 */
public class FileConversionData {


	// Formats for input and output files
	private FileFormats inFileFormat;
	private FileFormats outFileFormat;
	// Names of input and output files
	private String inFileName;
	private String outFileName;
	// Indicates whether a conversion has been requested or not
	private Boolean conversionAttempted = false;

	/**
	 * Constructor for the FileConverSionData class.
	 */
	public FileConversionData() {
		this.inFileFormat = null;
		this.outFileFormat = null;
		this.inFileName = null;
		this.outFileName = null;
	}

	/**
	 * Getter method for the input file format 
	 * @return the format of the input file
	 */
	public FileFormats getInFileFormat() {
		return inFileFormat;
	}


	/**
	 * Setter method for the input file format
	 * @param inFileFormat the format of the input file
	 */
	public void setInFileFormat(FileFormats inFileFormat) {
		this.inFileFormat = inFileFormat;
		conversionAttempted = true;
	}


	/**
	 * Getter method for the output file format 
	 * @return the format of the output file
	 */
	public FileFormats getOutFileFormat() {
		return outFileFormat;
	}


	/**
	 * Setter method for the output file format
	 * @param outFileFormat the format of the output file
	 */
	public void setOutFileFormat(FileFormats outFileFormat) {
		this.outFileFormat = outFileFormat;		
		conversionAttempted = true;
	}


	/**
	 * Getter method for the input file name 
	 * @return the name of the input file
	 */
	public String getInFileName() {
		return inFileName;
	}


	/**
	 * Setter method for the input file name
	 * @param inFileName the name of the input file
	 */
	public void setInFileName(String inFileName) {
		this.inFileName = inFileName;
		conversionAttempted = true;
	}


	/**
	 * Getter method for the output file name 
	 * @return the name of the output file
	 */
	public String getOutFileName() {
		return outFileName;
	}


	/**
	 * Setter method for the output file name
	 * @param outFileName the name of the output file
	 */
	public void setOutFileName(String outFileName) {
		this.outFileName = outFileName;
		conversionAttempted = true;
	}

	
	/**
	 * Getter method for conversionRequested
	 * @return true if a file conversion is attempted, false otherwise
	 */
	
	public boolean conversionRequested() {
		return conversionAttempted;
	}

	/**
	 * Checks if the combinations of file formats and file names are valid
	 * 
	 * @return null if the combination is valid, otherwise an error message
	 */
	
	public String validate() {
		if ((inFileFormat != null) &&
				(outFileFormat != null) &&
				(inFileName != null))
		{
			return null;
		} else {
			return "-f -t and -i must all be used when converting files";
		}
	}
}
