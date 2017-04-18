package com.coderising.litestruts.util;

import com.coderising.litestruts.dom.StrutsAction;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析struts.xml
 *
 * Created by Korben on 03/03/2017.
 */
public class StrutsParser {

    private static final String STRUTS_XML = "struts.xml";

    public static void main(String[] args) {
        Map<String, StrutsAction> strutsActions = doParse();
        System.out.println(strutsActions.size());
    }

    public static Map<String, StrutsAction> doParse() {
        Map<String, StrutsAction> resultMap = new HashMap<>();

        SAXReader reader = new SAXReader();
        InputStream in = getStrutsInputStream();
        try {
            Document document = reader.read(in);
            Element rootElement = document.getRootElement();

            // parse action element
            List<Element> elementActions = rootElement.elements();
            for (Element elementAction : elementActions) {
                StrutsAction action = new StrutsAction();

                // parse "name" attribute from action element
                resultMap.put(elementAction.attribute("name").getValue(), action);

                // parse "class" attribute from action element
                action.setActionClassName(elementAction.attribute("class").getValue());

                // parse sub elements in action element
                List<Element> elements = elementAction.elements();
                Map<String, String> map = new HashMap<>();
                for (Element element : elements) {
                    map.put(element.attribute("name").getValue(), element.getStringValue());
                }
                action.setAttributes(map);
            }

            return resultMap;
        } catch (DocumentException e) {
            throw new IllegalStateException("failed to parse " + STRUTS_XML, e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static InputStream getStrutsInputStream() {
        StrutsParser.class.getPackage().getName();
        InputStream in = StrutsParser.class.getClassLoader()
                .getResourceAsStream("com/coderising/litestruts/util/" + STRUTS_XML);
        if (in == null) {
            throw new IllegalStateException(STRUTS_XML + " doesn't exist");
        }

        return in;
    }
}
