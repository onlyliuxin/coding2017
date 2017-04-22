package org.xukai.coderising.litestruts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.xukai.coderising.util.XmlParseHelper;


public class StrutsTest {

    private String strutsPath = Thread.currentThread().getContextClassLoader().getResource("struts.xml").getPath()
            .substring(1);

	@Test
	public void testLoginActionSuccess() {
		
		String actionName = "login";
        
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","1234");
        
        
        View view  = Struts.runAction(actionName,params);        
        
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
        Assert.assertEquals("login successful", view.getParameters().get("message"));
	}

	@Test
	public void testLoginActionFailed() {
		String actionName = "login";
		Map<String,String> params = new HashMap<String,String>();
        params.put("name","test");
        params.put("password","123456"); //密码和预设的不一致
        
        View view  = Struts.runAction(actionName,params);        
        
        Assert.assertEquals("/jsp/showLogin.jsp", view.getJsp());
        Assert.assertEquals("login failed,please check your user/pwd", view.getParameters().get("message"));
	}

    @Test
    public void testInital() throws ClassNotFoundException {
        SAXReader sr = new SAXReader();//获取读取方式
        try {
            Document doc = sr.read(strutsPath);
            XmlParseHelper helper = new XmlParseHelper(doc);
            List<Element> actions = helper.getNodeByPath("//action");
            ArrayList<Action> actionsList = new ArrayList<Action>();
            for (Element action : actions){
                Action obj = new Action();
                String nameAttr = helper.getNodeAttrValue(action, "name");
                String classAttr = helper.getNodeAttrValue(action, "class");
                obj.setName(nameAttr);
                obj.setaClass(Class.forName(classAttr));
                List<Element> results = helper.getChildNodeByName(action, "result");
                for (Element result : results){
                    String resultNameAttr = helper.getNodeAttrValue(result, "name");
                    String resultValue = helper.getNodeValue(result);
                    HashMap<String, String> map = new HashMap<String,String>();
                    map.put("name",resultNameAttr);
                    map.put("viewPath",resultValue);
                    obj.setResultMapping(map);
                }
                actionsList.add(obj);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
