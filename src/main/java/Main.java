import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
		List<String> inputList = readFileInput();
		for(String str : inputList){
			System.out.println(str);
		}
		
		String input = "";
		input = readSystemInput();
		System.out.println(input);
        
		parseData();
	}

	private static List<String> readFileInput() throws FileNotFoundException {
		List<String> inputList = new ArrayList<>();
		try(FileInputStream fis = new FileInputStream(new File("input.txt"));
		    	BufferedReader br = new BufferedReader(new InputStreamReader(fis))){
			String str;
			while((str = br.readLine()) != null){
				inputList.add(str);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputList;
	}

	private static String readSystemInput() throws IOException {
		StringBuilder s = new StringBuilder();
		System.out.println("番号を入力してください。");
		System.out.print(">");
		while(true){
			char c = (char)System.in.read();
			if (c == '\n') {
				break;
			}
			s.append(c);
		}
		return s.toString();
	}

    
	private static void parseData() throws Exception {
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
}
