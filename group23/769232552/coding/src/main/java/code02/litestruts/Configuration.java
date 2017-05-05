package code02.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yaoyuan on 2017/3/21.
 */
public class Configuration {


    private String path;
    private final Map<String, ActionConfig> actionMap = new HashMap<String, ActionConfig>();

    Configuration(String path){
        parseXML(path);
    }

    //解析xml文件
    private void parseXML(String path){
        //读取文件
        File file = new File(path);
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();

        for (Iterator<Element> iterator = root.elementIterator("action"); iterator.hasNext();) {
            Element e = iterator.next();
            String actionName = e.attributeValue("name");
            String clazName = e.attributeValue("class");
            ActionConfig actionConfig = new ActionConfig(actionName,clazName);
            for(Iterator<Element> childIterator = e.elementIterator();childIterator.hasNext();){
                Element child = childIterator.next();
                String jspKey = child.attributeValue("name");
                String jspValue = child.getTextTrim();
                actionConfig.addViewResult(jspKey,jspValue);
            }
            actionMap.put(actionName,actionConfig);
        }
    }

    public String getView(String actionName, String result){
        String jspKey = actionName + "." + result;
        return actionMap.get(actionName).getViewName(result);
    }


    public Map<String, ActionConfig> getActionMap() {
        return actionMap;
    }

}
