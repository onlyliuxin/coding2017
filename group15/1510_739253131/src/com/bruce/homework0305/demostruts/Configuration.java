package com.bruce.homework0305.demostruts;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用来存放解析后的struts.xml数据
 */
public class Configuration {

    private Map<String, ActionConfig> actionConfigMap = new HashMap<>();

    public Configuration(String fileName){
        try {
            //拿到当前类的报名，拼接出struts.xml的路径，将文件读到输入流
            String path = this.getClass().getPackage().getName();
            path = path.replace(".", "/");
            InputStream is = this.getClass().getResourceAsStream("/" + path + "/" + fileName);
            //对输入流进行解析
            parseXml(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //用Jdom解析xml
    private void parseXml(InputStream is) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(is);
            Element root = document.getRootElement();
            List<Element> actions = root.getChildren("action");
            for(Element element: actions) {
                String actionName = element.getAttributeValue("name");
                String actionClz = element.getAttributeValue("class");
                ActionConfig ac = new ActionConfig(actionName, actionClz);
                List<Element> results = element.getChildren("result");
                for(Element result: results) {
                    String resultName = result.getAttributeValue("name");
                    String resultJsp = result.getValue();
                    ac.addViewResult(resultName, resultJsp);
                }
                actionConfigMap.put(actionName, ac);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过action的name值拿到对应的class路径
     * @param actionName
     * @return
     */
    public String getClassName(String actionName) {
        return actionConfigMap.get(actionName).getClassName();
    }

    /**
     * 根据action的name值和result的name值，拿到对应的jsp路径
     * @param actionName
     * @param resultName
     * @return
     */
    public String getResultView(String actionName, String resultName) {
        return actionConfigMap.get(actionName).getViewName(resultName);
    }

    /**
     * 内部静态类，用来存放struts.xml解析出来的action信息
     */
    private static class ActionConfig{
        private String name;
        private String clz;
        Map<String, String> results = new HashMap<>();

        public ActionConfig(String actionName,String clzName){
            this.name = actionName;
            this.clz = clzName;
        }

        public void addViewResult(String  resultName, String jspName){
            results.put(resultName, jspName);
        }

        public String getClassName() {
            return clz;
        }

        public String getViewName(String resultName) {
            return  results.get(resultName);
        }
    }
}
