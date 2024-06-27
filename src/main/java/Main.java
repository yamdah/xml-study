import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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
		List<String> inputList = new ArrayList<>();
		FileOutputStream os = new FileOutputStream("output.csv");
		OutputStreamWriter sw = new OutputStreamWriter(os);
		
		for (String str : parseData2()) {
			sw.write("電話番号");
			sw.write(str);
		}
		sw.flush();
		sw.close();

		parseData1();
		parseData2();
		parseData3();

		
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

	private static List<String> parseData2() throws Exception {
		FileInputStream is = new FileInputStream("telephoneNumber.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//telephone/number");
		NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		System.out.println(nodeList.getLength());
		int thirdCount = nodeList.getLength() - 1;

		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			System.out.print(element.getTextContent());
			if(i != thirdCount) {
				System.out.print("-");
			}
		}
		System.out.println("");

		List<String> returnList = new ArrayList();
		String telephoneNumber = null;
		for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				if (telephoneNumber == null) {
					telephoneNumber = element.getTextContent();
				} else {
					telephoneNumber += element.getTextContent();
				}
				if (i != thirdCount) {
					telephoneNumber += "-";
				}
		}
		returnList.add(telephoneNumber);

		return returnList;
	}

	private static void parseData3() throws Exception {
		FileInputStream is = new FileInputStream("amount.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile("//account/amount");
		NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
		int totalAmount = 0;
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			String amount1 = element.getTextContent();
			int amount2 = Integer.parseInt(amount1);
			totalAmount += amount2;
		}
		System.out.println(String.format("%,d", totalAmount));
	}
}
