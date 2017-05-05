package struts;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Created by IBM on 2017/3/4.
 */
public class StrutsUtils {

    public static Element getRoot(String filePath) {
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
            SAXReader saxReader = new SAXReader();
            return saxReader.read(resourceAsStream).getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException("初始化文件异常", e);
        }
    }

    public static boolean isEmpty(Object obj) {
        if (obj instanceof String)
            return obj == null || obj.equals("");
        else
            return obj == null;
    }
}
