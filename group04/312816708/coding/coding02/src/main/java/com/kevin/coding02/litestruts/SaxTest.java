package com.kevin.coding02.litestruts;

import com.kevin.coding02.model.ActionModel;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;

/**
 * Created by YinWenBing on 2017/2/28.
 */
public class SaxTest {

    public static void main(String[] args) throws Exception {
        //创建sax解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //获取Sax解析器
        SAXParser saxParser = factory.newSAXParser();
        //获取xml读取器
        XMLReader xmlReader = saxParser.getXMLReader();
        //设置内容处理器
        SaxUtil saxUtil = new SaxUtil();
        xmlReader.setContentHandler(saxUtil);
        //读取xml
        xmlReader.parse("src/main/java/com/kevin/coding02/litestruts/struts.xml");
        /*InputStream is = SaxTest.class.getClassLoader().getResourceAsStream("com/kevin/coding02/litestruts/struts.xml");
        xmlReader.parse(new InputSource(is));*/

        List<ActionModel> actions = saxUtil.getActions();
        System.out.println(actions);
    }
}
