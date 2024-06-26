import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) throws Exception {
		parseData1();
		parseData2();
	}

	private static void parseData1() throws Exception {
		FileInputStream is = new FileInputStream("bookList.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//list/book/name");
		NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        System.out.println(nodeList.getLength());

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			System.out.println(element.getTagName());
			System.out.println(element.getTextContent());
			
		}
	}

	private static void parseData2() throws Exception {
		FileInputStream is = new FileInputStream("telephoneNumber.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//telephone/number");
		NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		System.out.println(nodeList.getLength());

		for (int i = 0; i < nodeList.getLength(); i++) {
			int thirdCount = nodeList.getLength() - 1;
			Element element = (Element) nodeList.item(i);
			System.out.print(element.getTextContent());
			if(i != thirdCount) {
				System.out.print("-");
			}
			}
	}
}
