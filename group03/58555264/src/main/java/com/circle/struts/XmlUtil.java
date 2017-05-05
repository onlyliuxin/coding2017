package com.circle.struts;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by keweiyang on 2017/3/5.
 *  解析XML文件，向外提供Docuemnt对象
 */
public class XmlUtil {

    private XmlUtil() {

    }

    public static Document getDocument(String fileName) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //读当前文件路径下的
            Document doc = builder.parse(fileName);

            //去掉document的空格
            doc.normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


