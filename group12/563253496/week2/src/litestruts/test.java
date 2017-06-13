package litestruts;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Created by bdl19 on 2017/3/2.
 */
public class test {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read("src/litestruts/struts.xml");
            Element root = document.getRootElement();
            System.out.println(root.elements().size());


/*

            String s = "login";
            String string = "login";
            System.out.println(root.getName());
            Element action = root.element("action");
            System.out.print(action.getName());
            System.out.print(action.attribute("name").getText());
            System.out.print(action.attribute("name").getText());
            String str = action.attribute("name").getText();
            if (str.equals(s)) {
                System.out.println(123123);
                System.out.println("123");
                // Class clazz = Class.forName(action.attribute("class"));
            }
            System.out.println(s);
            System.out.println(str);

*/

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
