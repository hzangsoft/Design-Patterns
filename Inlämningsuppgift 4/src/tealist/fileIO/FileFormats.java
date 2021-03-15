package tealist.fileIO;


/**
 * Enumeration for supported file formats.
 * 
 * @author Thomas Ejnefjäll
 */
public enum FileFormats {
	TEXT("text", "text - Text (txt) file where fields are separated with ;", new TextFileIO()),
	XML("xml", "xml - Xml (xml) file", new XMLFileIO());

	private String fileFormat;
	private String fileFormatDescription;
	private TeaFileIO teaFileIO;

	/**
	 * Private constructor only for the enumeration itself
	 * 
	 * @param fileFormat Name of the file format
	 * @param description A short description of the file format.
	 * @param teaFileIO An object containing the interface for the file format. 
	 */
	private FileFormats(String fileFormat, String description, TeaFileIO teaFileIO) {
		this.fileFormat = fileFormat;
		this.fileFormatDescription = description;
		this.teaFileIO = teaFileIO;
	}


	/**
	 * Checks if the current file format is equal to another
	 * 
	 * @param fileFormat A string containing the other file format
	 * @return true it they are the same file format
	 */
	public boolean equals(String fileFormat) {
		return this.fileFormat.equalsIgnoreCase(fileFormat);
	}


	/**
	 * Checks if the current file format is equal to another
	 * 
	 * @param fileFormat The other file format
	 * @return true it they are the same file format
	 */
	public boolean equals(FileFormats fileFormat) {
		return this.equals(fileFormat.fileFormat);
	}


	/**
	 * Checks if the string contains a file format that is part of the enumeration
	 * 
	 * @param fileFormat a string containing the file format
	 * @return true if the file format is part of the enumeration
	 */
	public static boolean isValid(String fileFormat) {
		for (FileFormats ff : FileFormats.values()) {
			if(ff.equals(fileFormat)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Checks if the file format is part of the enumeration
	 * 
	 * @param fileFormat the file format we want to check
	 * @return true if the file format is part of the enumeration
	 */
	public static boolean isValid(FileFormats fileFormat) {
		return FileFormats.isValid(fileFormat.toString());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.fileFormat;
	}

	/**
	 * Prints the file format descriptions
	 * 
	 */
	public static void showFileFormats() {
		for (FileFormats ff : FileFormats.values()) {
			System.out.println(ff.fileFormatDescription);
		}
	}

	/**
	 * Performs the file format conversion
	 * 
	 * @param fileConverter information regarding the file formats and file names
	 * we want to use for the conversion
	 * @throws Exception if the input file doesn't exist or has the wrong format,
	 * or if something goes wrong when opening the output file
	 * 
	 */
	public static void convert(FileConversionData fileConverter) throws Exception {
		fileConverter.getOutFileFormat().teaFileIO.writeFile(
				fileConverter.getInFileFormat().teaFileIO.readFile(fileConverter.getInFileName()), fileConverter.getOutFileName());
	}
}
