package task2.litestruts;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Struts {

    public static View runAction(String actionName, Map<String, String> parameters) {

//        0. 读取配置文件struts.xml,引用Jsoup
        Document document = null;
        try {
            document = Jsoup.connect("http://my.977996067.cn/2017_2/struts.xml").get();
        } catch (IOException e) {
            System.err.println("xml解析失败");
        }
//        获取所有的action标签
        Elements actions = document != null ? document.select("action") : null;
        if (actions == null)
            return null;
        for (Element actionElement : actions) {
            String name = actionElement.attr("name");
//            反射action
            if (actionName.equals(name)) {
                try {
                    Class<?> actionClass = Class.forName(actionElement.attr("class"));
                    Object o = actionClass.getConstructor().newInstance();
                    parameters.forEach((k, v) -> {
                        try {
                            Field field = actionClass.getDeclaredField(k);
                            field.setAccessible(true);
                            //赋值
                            field.set(o, v);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
//                  执行execute方法,获取返回值
                    Method method = actionClass.getMethod("execute");
                    String returnValue = (String) method.invoke(o);
                    View view = new View();
                    Map map = new HashMap();
                    Arrays.stream(actionClass.getMethods())
                            .forEach(((Method m) -> {
                                try {
                                    String methodName = m.getName();
                                    if (methodName.startsWith("get"))
                                        map.put(methodName.substring(methodName.indexOf("get") + 3).toLowerCase(), m.invoke(o));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }));
                    view.setParameters(map);
//                    获取返回视图名
                    Elements children = actionElement.children().select("result");
                    for (Element aChildren : children) {
                        if (returnValue.equals(aChildren.attr("name"))) {
                            view.setJsp(aChildren.text());
                        }
                    }
                    System.out.println(view);
                    return view;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
