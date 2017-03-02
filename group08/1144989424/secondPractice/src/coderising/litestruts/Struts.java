package coderising.litestruts;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader; 



public class Struts {
    
    public static void main(String [] args) throws Exception{
        Document document = readStrutsXml("src/coderising/litestruts/struts.xml");
        Element rootElement = document.getRootElement();
        List<Element> childElements = rootElement.elements();
        for (Element child : childElements) {
            System.out.println("login" + child.elementText("login"));
        }
    }

    public static View runAction(String actionName, Map<String,String> parameters) throws Exception {

        /*
         
		0. 读取配置文件struts.xml
 		*/
        Document document = readStrutsXml("src/coderising/litestruts/struts.xml");
        //拿到根Element
        Element rootElement = document.getRootElement();
        //用根遍历节点得到Node
        List<Element> childElements = rootElement.elements();
        for (Element child : childElements) {
            String name = child.getName();
        }
        //根据Node名字获得值
        
        /*
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
    	
    	return null;
    }
    
    private static Document readStrutsXml(String filePath) throws Exception{
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        
        return document;
    }

}
