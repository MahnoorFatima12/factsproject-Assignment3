 import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Parser{
	private Handler handler;

	public Parser (String fileName){
		try{
			File file = new File(fileName);
			handler = new Handler();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser =  factory.newSAXParser();
			saxParser.parse (file, handler);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public FactList getFactList(){
		return handler.getList();
	}

    boolean saveFact(String fact, String author, String type,String xmlFile) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
            try {
                docBuilder = documentBuilderFactory.newDocumentBuilder();
                InputStream is = new FileInputStream(xmlFile);
                Document doc = docBuilder.parse(is);        
                Node root=doc.getFirstChild();
                Element newfact=doc.createElement("Fact");
                
                Element fact1=doc.createElement("fact-text");
                Node n = doc.createTextNode(fact);
                fact1.appendChild(n);
                        
                Element fact2=doc.createElement("author");
                n = doc.createTextNode(author);
                fact2.appendChild(n);
                
                
                
                Element fact3=doc.createElement("fact-type");
                n = doc.createTextNode(type);
                fact3.appendChild(n);
                
                
                
                
                
                newfact.appendChild(fact1);
                newfact.appendChild(fact2);
                newfact.appendChild(fact3);
                
                root.appendChild(newfact);
                
                
                System.out.println("Saved"  + root);
                
                DOMSource source = new DOMSource(doc);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                StreamResult result = new StreamResult(xmlFile);
                transformer.transform(source, result);
        
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
        return true;
    }
        
        
        
        
        
}
