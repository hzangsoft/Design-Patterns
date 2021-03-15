package tealist.fileIO;

import java.io.IOException;
import java.util.List;

import tealist.Tea;

/**
 * Interface for reading and writing different file formats for 
 * files containing information on teas.
 *  
 * @author Håkan Strääf
 */

public interface TeaFileIO {

	/**
	 * Reads tea information from a  file. The file should be in UTF-8
	 * format if it contains any special characters.
	 * 
	 * @param fileName Name of the file
	 * @return A list of the tea in the file
	 * @throws IOException If there was a I/O error
	 */
	public List<Tea> readFile(String fileName) throws IOException;
	
	/**
	 * Writes the tea information to a file.
	 * 
	 * @param teaList A list of the tea
	 * @param fileName Name of the file, if it is null the file will be written to 
	 *                 system.out
	 * @throws Exception If there was an I/O error
	 */
	public void writeFile(List<Tea> teaList, String fileName) throws Exception;
	
	
}
