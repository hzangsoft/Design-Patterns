package tealist.fileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import tealist.Tea;

/**
 * Implements the TeaFileIO interface for text files
 * 	
 * The text file must be formatted as following: 
 * category;name;price;description[new line]
 * and should be in UTF-8 if it contains any special characters.
 * 
 * @author Håkan Strääf
 *
 */

public class TextFileIO implements TeaFileIO {

	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Tea> readFile(String fileName) throws IOException {
		List<Tea> teaList = new ArrayList<Tea>();

		File file = new File(fileName);

		if(!file.exists() || !file.isFile())
		{
			throw new IOException("The file " + fileName +  " does not exist");
		}
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		String line;

		try
		{
			while((line = br.readLine()) != null)
			{
				Tea tea = new Tea();
				String[] teaLine = line.split(";");
				tea.setCategory(teaLine[0]);
				tea.setName(teaLine[1]);
				tea.setPrice(Integer.valueOf(teaLine[2]));
				tea.setDescription(teaLine[3]);
				teaList.add(tea);

			}
			br.close();
		} catch (Exception e)
		{			
			throw new IOException("Input file (" + fileName + ") not correct format");
		}		
		return teaList;

	}

	/**
	 * {@inheritDoc}
	 */
	
	@Override
	public void writeFile(List<Tea> teaList, String fileName) throws Exception {
		if(fileName == null) 
		{
			for(Tea tea : teaList)
			{
				System.out.println(tea.getCategory() + ";" + tea.getName() + ";" + tea.getPrice() + ";" + tea.getDescription());
			}
		}
		else 
		{
			File file = new File(fileName);

			if(!file.exists())
			{
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			for(int i = 0; i < teaList.size(); i ++)  
			{
				Tea tea = teaList.get(i);
				bw.write(tea.getCategory() + ";" + tea.getName() + ";" + tea.getPrice() + ";" + tea.getDescription());

				if(i + 1 != teaList.size())
				{
					bw.newLine();
				}
			}		
			bw.close();			
		}	

	}
}
