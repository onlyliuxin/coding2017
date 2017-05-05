package struts;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IBM on 2017/3/4.
 */
public class LoginActionTest {

    @Test
    public void fun1() throws DocumentException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("struts/struts.xml");
        SAXReader reader = new SAXReader();
        Element rootElement = reader.read(resourceAsStream).getRootElement();
        Element selectedElement = (Element) rootElement.selectSingleNode("//action[@name='login']");
        System.out.println(selectedElement.attributeValue("class"));

    }

    @Test
    public void fun2() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("name", "test");
        parameters.put("password", "1234");
        View view = Struts.runAction("login", parameters);
        Assert.assertEquals("/jsp/homepage.jsp", view.getJsp());
    }
}
