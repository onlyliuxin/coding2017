package util;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import static util.Print.*;

public class XMLreader {
	
	private static String address = "src/litestruts/struts.xml";
	
    public Document parse(String add) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(add);
        return document;
    }

    public String parseClass(String attr) {
    	
    	Document doc = null;
		try {
			doc = parse(address);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
    	
         Node root = doc.getRootElement();
         List list = root.selectNodes("action[@name='" + attr + "']");
         String clazz = null;
         for(Object o:list){
             Element e = (Element) o;
             clazz=e.attributeValue("class");
             System.out.println("class = " + clazz);
         }
		return clazz;
    }
    

    
    public String parseResult(String attr, String result) {
    	
    	Document doc = null;
		try {
			doc = parse(address);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
    	
         Node root = doc.getRootElement();
         List list = root.selectNodes("action[@name='" + attr + "']/result[@name='" + result + "']");
       
         String jsp = null;
         for(Object o:list){
             Element e = (Element) o;
             jsp = e.getTextTrim();
         }
		return jsp;
    }
    
    

    public static void main(String args[]) throws DocumentException {
    	XMLreader reader = new XMLreader();
    	reader.parseClass("login");

    }
    
}
