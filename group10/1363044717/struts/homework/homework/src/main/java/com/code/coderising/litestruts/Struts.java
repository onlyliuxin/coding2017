package com.code.coderising.litestruts;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
        SAXReader reader = new SAXReader();
        View view=new View();
        try {
            Document e = reader.read(ClassLoader.getSystemResource("struts.xml"));
            //得到根节点
            Element struts = e.getRootElement();
            Iterator it = struts.elementIterator();
            while(it.hasNext()) {//action遍历
                Element action = (Element)it.next();
                // 找到 name=actionName 的action
                List<Attribute> actionAtr = action.attributes();
                if(!actionAtr.get(0).getValue().equals(actionName))
                    continue;
                String className= actionAtr.get(1).getValue();
                //得到actionName 对应的class
                Class classz=Class.forName(className);
                Object o=classz.newInstance();
                Field field;
                for(Map.Entry<String, String> entry:parameters.entrySet()){
                    field = classz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(o, entry.getValue());
                }
                Method m = classz.getMethod("execute");
                String resultString=(String)m.invoke(o);
                Method m2 = classz.getMethod("getMessage");
                String message=(String)m2.invoke(o);

                Map<String,String> map=new HashMap<>();

                map.put("message",message);
                view.setParameters(map);
                it=action.elementIterator();
                //遍历 <result>
                while (it.hasNext()){
                    Element result = (Element)it.next();
                    String s= result.attribute(0).getValue();
                    if(resultString.equals(s)){
                        view.setJsp(result.getStringValue());
                        return view;
                    }
                }
            }
        } catch (DocumentException var9) {
            var9.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
