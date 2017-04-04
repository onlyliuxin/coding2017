package com.coderising.litestruts;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;
public class SAXmain {
  public static void main(String args[]) {
    File xmlFile = new File("struts.xml");
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = null;
    spf.setNamespaceAware(true);
    spf.setValidating(true);
    try {
     parser = spf.newSAXParser();
     SAXmain handler = new SAXmain(); 
     parser.parse(xmlFile, handler);
    }
    catch(Exception e) {
      e.printStackTrace(System.err);    }      }
}
