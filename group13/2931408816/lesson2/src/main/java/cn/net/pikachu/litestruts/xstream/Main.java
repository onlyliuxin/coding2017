package cn.net.pikachu.litestruts.xstream;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pikachu on 17-3-2.
 */
public class Main {
    public static void main(String[] args) {
        Result result = new Result("success","/index.jsp");
        List<Result> results = new LinkedList<>();
        results.add(result);
        results.add(new Result("fail","/login.jsp"));

        Action action = new Action(Main.class.getSimpleName(),Main.class.getCanonicalName(),results);
        List<Action> actions = new ArrayList<>();
        actions.add(action);
//        actions.add(action);

        Struts struts = new Struts(actions);

        XStream xStream = new XStream();
        xStream.processAnnotations(new Class[]{
                Struts.class,Result.class,Action.class
        });
//        xStream.addImplicitCollection(Struts.class,"actions");
//        xStream.alias("struts",Struts.class);
//        xStream.useAttributeFor("name",String.class);
//        xStream.useAttributeFor("clazz",String.class);
//        xStream.aliasField("class",Struts.class,"clazz");
//        xStream.alias("action",Action.class);
//        xStream.alias("result",Result.class);

        String xml = xStream.toXML(struts);

        System.out.println(xml);
    }
}
