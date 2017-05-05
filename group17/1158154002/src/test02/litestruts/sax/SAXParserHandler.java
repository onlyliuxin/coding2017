package test02.litestruts.sax;

import java.util.HashMap;

import test02.litestruts.Action;

public interface SAXParserHandler {
    void startDocument();
    void startElement(String tagName, HashMap<String, String> attributes);
    void endElement(String tagName);
    Action endDocument();
    void innerText(String innerText);
}