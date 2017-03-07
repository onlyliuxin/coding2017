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
 * ������ģ���ȡ����һ��struts.xml�ļ�
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
	// ����action�ļ���
	private Map<String,ActionMapping> allActions = new HashMap<String,ActionMapping>();
	public readStrutsXml() {
		this.init();
	}
	
	// ��ʼ��allActions����
	private void init() {
		/********DOM4J��ȡ�����ļ�***********/
		System.out.println("���ڶ�ȡmystruts.xml�ļ�");
		try {
			// �õ�������
			SAXReader reader = new SAXReader();
			// �õ�src/mystruts.xml  �ļ���
			InputStream inStream = this.getClass().getResourceAsStream("/mystruts.xml");
			//  �����ļ�
			Document doc = reader.read(inStream);
			//  ��ȡ��
			Element root = doc.getRootElement();
			// �õ�package�ڵ�
			Element ele_package = root.element("package");
			//  �õ�package�ڵ��£�  ���е�action�ӽڵ�
			List<Element> listAction = ele_package.elements("action");
			// ���� ����װ
			for (Element ele_action : listAction) {
				//  ��װһ��ActionMapping����
				ActionMapping actionMapping = new ActionMapping();
				actionMapping.setName(ele_action.attributeValue("name"));
				actionMapping.setClassName(ele_action.attributeValue("class"));
				actionMapping.setMethod(ele_action.attributeValue("method"));
				
				// ��װ��ǰaciton�ڵ������еĽ����ͼ
				Map<String,Result> results = new HashMap<String, Result>();
				
				// �õ���ǰaction�ڵ������е�result�ӽڵ�
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // ��ǰ������ÿһ��Ԫ�ض��� <result...>
					 Element ele_result = it.next();
					 // ��װ����
					 Result res = new Result();
					 res.setName(ele_result.attributeValue("name"));
					 res.setType(ele_result.attributeValue("type"));
					 res.setPage(ele_result.getTextTrim());
					 // ��ӵ�����
					 results.put(res.getName(), res);
				 }
				// ���õ�actionMapping��
				actionMapping.setResults(results);
				
				// 6.x actionMapping��ӵ�map����
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
			System.out.println("struts.xml�ļ���ȡ���");
			
		} catch (Exception e) {
			throw new RuntimeException("����ʱ���ʼ������",e);
		}
	}
	
	
}
