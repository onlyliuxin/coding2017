package basic.liteStruts;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * @author : 温友朝
 * @date : 2017/4/10
 */
public class ReadXML {
    Document document;

    public ReadXML(String filePath) throws Exception{
        SAXReader reader = new SAXReader();
        document = reader.read(new File(ReadXML.class.getResource("/").getFile()) + filePath);
    }

    public String getActionClass(String actionName){
        return document.selectSingleNode("//action[@name='" + actionName + "']").valueOf("@class");
    }

    public String getJspPage(String actionName, String result){
        return document.selectSingleNode("//action[@name='" + actionName + "']")
                .selectSingleNode("//result[@name='" + result + "']").getText();
    }
}
