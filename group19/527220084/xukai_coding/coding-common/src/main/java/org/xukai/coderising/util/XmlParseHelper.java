package org.xukai.coderising.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;

import java.util.List;

/**
 * @author xukai
 * @desc
 * @date 2017-02-27-下午 2:28
 */
public class XmlParseHelper {

    private Document doc;

    private DefaultXPath xPath;

    public XmlParseHelper(Document doc) {
        if (doc == null) {
            throw new RuntimeException("构造xml解析器出错");
        }
        this.doc = doc;
    }

    public List<Element> getNodeByPath(String path){
        xPath = new DefaultXPath(path);
        return xPath.selectNodes(doc);
    }

    public List<Element> getChildNodeByName(Element parentNode,String childNodeName){
        xPath = new DefaultXPath("./" + childNodeName);
        return xPath.selectNodes(parentNode);
    }

    public String getNodeAttrValue(Element node,String attrName){
        return node.attributeValue(attrName);
    }

    public String getNodeValue(Element node){
        return node.getTextTrim();
    }
}
