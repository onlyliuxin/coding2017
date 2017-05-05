package rui.study.coding2017.coderising.liteststruts;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取struct.xml.解析xml
 * Created by 赵睿 on 2017/3/4.
 */
public class StrutsAction {
    private String name;
    private Class aClass;
    private List<Result> results;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    class Result{
        String name;
        String jspPath;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
    public void setResult(Result result) {
        this.results.add(result);
    }

    private static volatile Map<String ,Object> strutsMap=new HashMap<String,Object>();

    static void putStrutsMap(String key,Object obj){
        if(strutsMap.get(key)==null){
            synchronized (key){
                if(strutsMap.get(key)==null){
                    synchronized (key){
                        strutsMap.put(key,obj);
                    }
                }
            }
        }
    }

    static StrutsAction getFormStrutsMap(String key){
        return (StrutsAction) strutsMap.get(key);
    }

    //0. 读取配置文件struts.xml
    static {
        try {
            File strutsFile=new File(Struts.class.getResource("/struts.xml").getPath());
            Document document= new SAXReader().read(strutsFile);
            Element element=document.getRootElement();
            if(!element.getName().equals("struts")) throw new IOException("不是一个struts.xml的配置文件");
            StrutsAction.listNodes(element,null);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static void listNodes(Element element, Object object) throws ClassNotFoundException {
        List<Element> elements=element.elements();
        for (Element childEle:elements) {
            if(childEle.getName().equals("action")) dealAction(childEle);
            if(childEle.getName().equals("result")) dealResult(childEle, (StrutsAction) object);
        }

    }

    static void dealAction(Element action) throws ClassNotFoundException {
        StrutsAction strutsAction=new StrutsAction();
        List<Attribute> attributes=action.attributes();
        for (Attribute attribute:attributes) {
            if(attribute.getName().equals("name")) strutsAction.setName(attribute.getValue());
            if(attribute.getName().equals("class")) strutsAction.setaClass(Class.forName(attribute.getValue()));
        }
        List<StrutsAction.Result> results=new ArrayList<Result>(action.elements().size());
        strutsAction.setResults(results);
        listNodes(action,strutsAction);
        putStrutsMap(strutsAction.getName(),strutsAction);
    }

    static void dealResult(Element element,StrutsAction strutsAction) {
        StrutsAction.Result result=strutsAction.new Result();
        List<Attribute> attributes=element.attributes();
        for (Attribute attribute:attributes) {
            if(attribute.getName().equals("name")) result.name=attribute.getValue();
        }
        result.jspPath=element.getStringValue();
        strutsAction.setResult(result);
    }
}


