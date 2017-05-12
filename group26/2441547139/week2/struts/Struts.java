package week2.struts;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class Struts {

    public static void main(String[] args) {
        runAction();
    }

    public static void runAction() {

        /*
         
		0. 读取配置文件struts.xml
 		
 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		-
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。
        
        */
        File file = new File("src/week2/struts/struts.xml");
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            Element rootElement = document.getRootElement();
//            List<Element> elements = rootElement.elements("action");
//            for (Element element : elements) {
//                String aClass = element.attributeValue("class");
//                Class firstClass = Class.forName(aClass);
//                Object obj = firstClass.newInstance();
//                Method setName = firstClass.getMethod("setName",String.class);
//                Method setPassWord = firstClass.getMethod("setPassword",String.class);
//                Method execute = firstClass.getMethod("execute");
//                setName.invoke(obj , "test");
//                setPassWord.invoke(obj , "1234");
//                String result = (String) execute.invoke(obj);
//                System.out.println(result);
//            }
            //根节点的子节点
            List<Element> elements = rootElement.elements();
            for(Element element : elements) {
                //节点的属性
                List<Attribute> attributes = element.attributes();
                List<Element> list = element.elements();
                for(Element e : list) {
                    if(e.attributeValue("name").equals("success")) {
                        String jsp = e.getTextTrim();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
