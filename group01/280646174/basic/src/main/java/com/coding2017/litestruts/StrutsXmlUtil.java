package com.coding2017.litestruts;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by kaitao.li on 2017/3/4.
 */
public class StrutsXmlUtil {

    public static StrutsDefinition parseResource(InputStream inputStream) {

        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            throw new RuntimeException("解析xml出错");
        }

        // 获取根节点对象
        Element rootElement = document.getRootElement();
        StrutsDefinition strutsDefinition = new StrutsDefinition();
        Iterator<Element> actionIterator = rootElement.elements("action").iterator();
        List<StrutsDefinition.ActionDefinition> actionDefinitions = new ArrayList<StrutsDefinition.ActionDefinition>();
        strutsDefinition.setActionDefinitionList(actionDefinitions);
        while (actionIterator.hasNext()) {
            Element actionElement = actionIterator.next();
            StrutsDefinition.ActionDefinition actionDefinition = new StrutsDefinition.ActionDefinition();
            actionDefinition.setName(actionElement.attributeValue("name"));
            actionDefinition.setClazz(actionElement.attributeValue("class"));
            actionDefinitions.add(actionDefinition);

            Iterator<Element> resultIterator = actionElement.elements("result").iterator();
            List<StrutsDefinition.ResultDefinition> resultDefinitions = new ArrayList<StrutsDefinition.ResultDefinition>();
            actionDefinition.setResultDefinitions(resultDefinitions);
            while (resultIterator.hasNext()) {
                Element resultElement = resultIterator.next();
                StrutsDefinition.ResultDefinition resultDefinition = new StrutsDefinition.ResultDefinition();
                resultDefinition.setName(resultElement.attributeValue("name"));
                resultDefinition.setValue(resultElement.getTextTrim());
                resultDefinitions.add(resultDefinition);
            }
        }
        return strutsDefinition;
    }

    public static void main(String[] args) {
        StrutsXmlUtil.parseResource(StrutsXmlUtil.class.getResourceAsStream("/struts.xml"));
    }
}