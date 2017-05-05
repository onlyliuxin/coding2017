package cn.net.pikachu.litestruts.xstream;

import com.thoughtworks.xstream.XStream;

import java.io.File;

/**
 * Created by pikachu on 17-3-2.
 */
public class Test {
    public static void main(String[] args) {
        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{
                Struts.class,Result.class,Action.class
        });
        Struts struts = (Struts) xStream.fromXML(new File("/home/pikachu/Documents/2017编程提高/coding2017/group13/2931408816/lesson2/src/main/java/cn/net/pikachu/litestruts/struts.xml"));
    }
}
