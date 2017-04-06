package com.coding.week2.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public class StrutsXmlUtil {

    private static final String RESOURCE_PATH = "src/main/resources";
    private static final String STRUTS_CONFIG_FILE_NAME = "struts.xml";

    private StrutsXmlUtil() {
    }

    public static Document readXml(String path) throws DocumentException {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(new File(path));
        } catch (DocumentException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public static Iterator actionIterator(String path) throws DocumentException {
        Document doc = readXml(path);
        Element root = doc.getRootElement();
        return root.elementIterator("action");
    }

    public static String getActionClassName(String actionName) throws DocumentException {
        Iterator iterator = actionIterator(getPathOfStrutsConfigurtion());
        if(actionName == null || "".equals(actionName)){
            throw new IllegalArgumentException("actionName can't be empty");
        }
        while (iterator.hasNext()){
            Element e =(Element) iterator.next();
            if(actionName.equals(e.attributeValue("name"))){
                return e.attributeValue("class");
            }
        }
        return null;
    }


    public static String getPathOfStrutsConfigurtion(){
        File file = new File(RESOURCE_PATH);
        List<File> fileList = new ArrayList<>();

        if (file.isDirectory()){
            File[] files = file.listFiles((dir, name) -> {
               return name.equals(STRUTS_CONFIG_FILE_NAME);
            });
            fileList.addAll(Arrays.asList(files));
            if(fileList.size() > 1){
                throw new RuntimeException("配置文件不止一个");
            }else if(fileList.size() == 0){
                throw new RuntimeException("找不到struts配置文件");
            }
        }
        return fileList.get(0).getPath();
    }

    public static Map<String,String> getResultOfAction(String actionName) throws DocumentException {
        Iterator iterator = actionIterator(getPathOfStrutsConfigurtion());
        Map<String, String> res = new HashMap<>();
        while (iterator.hasNext()){
            Element e =(Element) iterator.next();
            System.out.println(e.getName());
            if(actionName.equals(e.attributeValue("name"))){
                Iterator resItr = e.elementIterator("result");
                while (resItr.hasNext()){
                    Element result = (Element)resItr.next();
                    System.out.println(result.attribute("name").getValue());
                    System.out.println(result.getData());
                    res.put(result.attribute("name").getValue(), result.getData().toString());
                }

            }
        }
        return res;
    }

}
