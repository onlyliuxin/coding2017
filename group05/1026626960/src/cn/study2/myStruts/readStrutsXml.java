package cn.study2.myStruts;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 本程序模拟读取类似一下struts.xml文件
 *
 <mystruts>
	<package>
		
		<action name="login" class="LoginAction" method="login">
			<result name="loginSuccess" type="redirect">/index.jsp</result>
			<result name="loginFaild">/login.jsp</result>
		</action>
		
		<action name="register" class="RegisterAction" method="register">
			<result name="registerSuccess">/login</result>
		</action>
		
	</package>

</mystruts>
 * @author zhengliang
 *
 */
public class readStrutsXml {
	// 保存action的集合
	private Map<String,ActionMapping> allActions = new HashMap<String,ActionMapping>();
	public readStrutsXml() {
		this.init();
	}
	
	// 初始化allActions集合
	private void init() {
		/********DOM4J读取配置文件***********/
		System.out.println("正在读取mystruts.xml文件");
		try {
			// 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			InputStream inStream = this.getClass().getResourceAsStream("/mystruts.xml");
			//  加载文件
			Document doc = reader.read(inStream);
			//  获取根
			Element root = doc.getRootElement();
			// 得到package节点
			Element ele_package = root.element("package");
			//  得到package节点下，  所有的action子节点
			List<Element> listAction = ele_package.elements("action");
			// 遍历 ，封装
			for (Element ele_action : listAction) {
				//  封装一个ActionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				actionMapping.setName(ele_action.attributeValue("name"));
				actionMapping.setClassName(ele_action.attributeValue("class"));
				actionMapping.setMethod(ele_action.attributeValue("method"));
				
				// 封装当前aciton节点下所有的结果视图
				Map<String,Result> results = new HashMap<String, Result>();
				
				// 得到当前action节点下所有的result子节点
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // 当前迭代的每一个元素都是 <result...>
					 Element ele_result = it.next();
					 // 封装对象
					 Result res = new Result();
					 res.setName(ele_result.attributeValue("name"));
					 res.setType(ele_result.attributeValue("type"));
					 res.setPage(ele_result.getTextTrim());
					 // 添加到集合
					 results.put(res.getName(), res);
				 }
				// 设置到actionMapping中
				actionMapping.setResults(results);
				
				// 6.x actionMapping添加到map集合
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
			System.out.println("struts.xml文件读取完毕");
			
		} catch (Exception e) {
			throw new RuntimeException("启动时候初始化错误",e);
		}
	}
	
	
}
