package week2;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
/**
 * Created by dwang on 2017/2/28.
 */
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

        try{
            //创建实例
            File f = new File("struts.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element memberElm=root.element(actionName);// 获取actionName的节点
            Attribute attribute=memberElm.attribute("class");    // 属性名name .
            String test = attribute.getText();
            Class a=Class.forName(test);
            Object obj = a.newInstance();

            //调用set方法
            Set keys= parameters.keySet();
            String methodName;
            if(keys!=null) {
                Iterator iterator = keys.iterator();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    Object value = parameters.get(key);
                    methodName = "set" + key;
                    Method method = a.getMethod(methodName, String.class);
                    method.invoke(a, value);
                }
            }
               //调用execute方法
                Method m=a.getMethod("execute");
                Object message=m.invoke(a);
                String msg=message.toString();

            //调用get方法
            Map map = new HashMap();
            Set getkeys= parameters.keySet();
            String methodName;
            if(getkeys!=null) {
                Iterator iterator = getkeys.iterator();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    Object value = parameters.get(key);
                    map.put(key, value);
                }
            }
            map.put(message, msg);
            //通过msg找到对应的JSP页面
            List nides = rootElm.elements("result");
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
