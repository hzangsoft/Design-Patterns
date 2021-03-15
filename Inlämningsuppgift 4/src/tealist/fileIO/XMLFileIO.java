package tealist.fileIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tealist.Tea;

/**
 * Implements the TeaFileIO interface for XML files
 *
 * The xml file must have elements named tea containing
 * sub elements with the tags category, name, price and description
 * 
 * @author Håkan Strääf
 */

public class XMLFileIO implements TeaFileIO {


	/**
	 * {@inheritDoc}
	 */

	@Override
	public List<Tea> readFile(String fileName) throws IOException {

		List<Tea> teaList = new ArrayList<Tea>();

		File xmlFile = new File(fileName);
		if(!xmlFile.exists() || !xmlFile.isFile())
		{
			throw new IOException("The file " + fileName +  " does not exist");
		}

		try
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName(Tea.TEA);


			for(int i = 0; i < nodeList.getLength(); i++) {

				Tea tea = new Tea();
				Node node = nodeList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE) {

					Element element = (Element) node;

					tea.setCategory(getTagValue(Tea.CATEGORY, element));
					tea.setName(getTagValue(Tea.NAME, element));
					tea.setPrice(Integer.valueOf(getTagValue(Tea.PRICE, element)));
					tea.setDescription(getTagValue(Tea.DESCRIPTION, element));	 
				}
				teaList.add(tea);
			}
		}
		catch(Exception e) {
			throw new IOException("Input file (" + fileName + ") not correct format");
		}
		return teaList;
	}


	/**
	 * Gets a value from an element.
	 *  
	 * @param tag The tag we are looking for
	 * @param element The current element
	 * @return The value of the tag
	 */
	private String getTagValue(String tag, Element element) {
		NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}
	

	/**
	 * {@inheritDoc}
	 */
		@Override
	public void writeFile(List<Tea> teaList, String fileName) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement(Tea.TEALIST);
		doc.appendChild(rootElement);

		for(Tea tea : teaList) {
			Element teaElement = doc.createElement(Tea.TEA);
			rootElement.appendChild(teaElement);

			Element categoryElement = doc.createElement(Tea.CATEGORY);
			categoryElement.appendChild(doc.createTextNode(tea.getCategory()));
			teaElement.appendChild(categoryElement);

			Element nameElement = doc.createElement(Tea.NAME);
			nameElement.appendChild(doc.createTextNode(tea.getName()));
			teaElement.appendChild(nameElement);

			Element priceElement = doc.createElement(Tea.PRICE);
			priceElement.appendChild(doc.createTextNode("" + tea.getPrice()));
			teaElement.appendChild(priceElement);

			Element descriptionElement = doc.createElement(Tea.DESCRIPTION);
			descriptionElement.appendChild(doc.createTextNode(tea.getDescription()));
			teaElement.appendChild(descriptionElement);
		}			

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result;

		if(fileName == null) {
			result = new StreamResult(System.out);
		}
		else {
			result = new StreamResult(new File(fileName));
		}

		transformer.transform(source, result);	
	}

}
