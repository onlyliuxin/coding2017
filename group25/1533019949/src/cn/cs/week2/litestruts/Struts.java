package cn.cs.week2.litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class Struts {

    public static View runAction(String actionName, Map<String,String> parameters) {
    /*
    0. 读取配置文件struts.xml
    1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
    据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
    ("name"="test" ,  "password"="1234") ,
    那就应该调用 setName和setPassword方法
    2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
    3. 通过反射找到对象的所有getter方法（例如 getMessage）,
    通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
    放到View对象的parameters
    4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
    放到View对象的jsp字段中。
    */
      SAXReader reader = new SAXReader();
      Document doc = null;
      try {
        doc = reader.read("./out/production/1533019949/cn/cs/week2/litestruts/struts.xml");
      } catch (DocumentException e) {
        e.printStackTrace();
      }
      Element root = doc.getRootElement();

      for(Iterator i = root.elementIterator();i.hasNext();){
        Element element = (Element)i.next();
        if(element.attributeValue("name").equals(actionName)){
          Class actionClass = null;
          Object obj = null;
          Map viewParaMap = new HashMap<String,String>();
          try {
            actionClass = Class.forName(element.attributeValue("class"));
            obj = actionClass.newInstance();
            for(String s:parameters.keySet()){
              Method setterMethod = actionClass.getMethod("set"+UpperName(s),String.class);
              setterMethod.invoke(obj,parameters.get(s));
            }
            Method executorMethod = actionClass.getMethod("execute");
            String result = (String)executorMethod.invoke(obj);
            Method[] methods = actionClass.getMethods();//全部公共方法
            for(Method m:methods){
              if(m.getName().startsWith("get")){
                if(m.getName().equals("getClass")) continue;
                String attr = LowerName(m.getName().substring(3,m.getName().length()));
                String value = (String)m.invoke(obj);
                viewParaMap.put(attr,value);
              }
            }
            //遍历result节点
            for(Iterator ii = element.elementIterator();ii.hasNext();){
              Element ele = (Element)ii.next();
              if(ele.attributeValue("name").equals(result)){
                String jspPath = ele.getText();
                View view = new View();
                view.setJsp(jspPath);
                view.setParameters(viewParaMap);
                return view;
              }
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      return null;
    }

    private static String UpperName(String s){
      char[] cs = s.toCharArray();
      cs[0] -= 32;
      return String.valueOf(cs);
    }

    private static String LowerName(String s){
      char[] cs = s.toCharArray();
      cs[0] += 32;
      return String.valueOf(cs);
    }
}
