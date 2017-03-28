package com.coderising.litestruts.parser.xml;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

/**
 * Created by huitailang on 17/3/5.
 * Xpath解析测试
 */
public class XPathParserTest {
    @Test
    public void shouldTestXPathParserMethods() throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("nodelet_test.xml");
        XPathParser parser = new XPathParser(inputStream, false, null, null);
        assertEquals((Long) 1970l, parser.evalLong("/employee/birth_date/year"));
        assertEquals((short) 6, (short) parser.evalShort("/employee/birth_date/month"));
        assertEquals((Integer) 15, parser.evalInteger("/employee/birth_date/day"));
        assertEquals((Float) 5.8f, parser.evalFloat("/employee/height"));
        assertEquals((Double) 5.8d, parser.evalDouble("/employee/height"));
        assertEquals("${id_var}", parser.evalString("/employee/@id"));
        assertEquals(Boolean.TRUE, parser.evalBoolean("/employee/active"));
        assertEquals("<id>${id_var}</id>", parser.evalNode("/employee/@id").toString().trim());
        assertEquals(7, parser.evalNodes("/employee/*").size());
        XNode node = parser.evalNode("/employee/height");
        assertEquals("employee/height", node.getPath());
        assertEquals("employee[${id_var}]_height", node.getValueBasedIdentifier());
    }

    @Test
    public void testXPathParserMethods(){
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("struts.xml");
        XPathParser parser = new XPathParser(inputStream, false, null, null);
        assertEquals(2, parser.evalNodes("/struts/*").size());
        XNode node0 = parser.evalNodes("/struts/*").get(0);
        assertEquals("login", node0.evalString("/struts/action/@name"));
        assertEquals("com.coderising.litestruts.action.LoginAction", node0.evalString("/struts/action/@class"));
        XNode subNode0 = node0.evalNodes("/*").get(0);
        assertEquals("success", subNode0.evalString("/struts/action/result/@name"));
        assertEquals("/jsp/homepage.jsp", subNode0.evalString("/struts/action/result"));
    }
}
