package com.coding.litestruts;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bobi on 2017/4/1.
 * at code2017
 */
public class Configuration {
    Map<String, ActionConfig> actions = new HashMap<>();



    public Configuration(String path) throws IOException {

        URL pathName = Configuration.class.getClassLoader().getResource(path);

        assert pathName != null;
//        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);    默认则是从ClassPath根下获取，path不能以’/'开头
//        InputStream is = this.getClass().getResourceAsStream("/" + path);                // path 不以’/'开头时默认是从此类所在的包下取资源，以’/'开头则是从ClassPath根下获取
//        InputStream is = pathName.openStream();
        InputStream is = new FileInputStream(pathName.getPath());

        parseXML(is);

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXML(InputStream is) {

        SAXReader reader = new SAXReader();

        try {

            Document doc = reader.read(is);
            Element root = doc.getRootElement();
            Iterator it = root.elementIterator("action");

            while (it.hasNext()){

                Element ActionElement = (Element) it.next();

                String actionName = ActionElement.attributeValue("name");
                String className = ActionElement.attributeValue("class");

                ActionConfig ac = new ActionConfig(actionName, className);

                Iterator it2 = ActionElement.elementIterator();

                while (it2.hasNext()){

                    Element resultElement = (Element) it2.next();

                    String resultName = resultElement.attributeValue("name");
                    String viewName = resultElement.getTextTrim();

                    ac.addViewResult(resultName, viewName);
                }

                this.actions.put(actionName, ac);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public String getClassName(String actionName) {
        ActionConfig ac = actions.get(actionName);
        if (ac == null) {
            return null;
        }
        return ac.getClassName();
    }

    public String getResultView(String actionName, String resultName) {
        ActionConfig ac = actions.get(actionName);
        if (ac == null) {
            return null;
        }
        return ac.getViewName(resultName);

    }


    private static class ActionConfig{
        String name;
        String clzName;
        Map<String, String> viewResult = new HashMap<>();

        public ActionConfig(String name, String clzName) {
            this.name = name;
            this.clzName = clzName;
        }

        public String getClassName() {
            return clzName;
        }

        public void addViewResult(String name, String viewName){
            viewResult.put(name, viewName);

        }

        public String getViewName(String resultName){
            return viewResult.get(resultName);
        }
    }
}
