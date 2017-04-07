package com.coderising.litestruts;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Struts {

    private static Struts instance = null;
    private static Map<String, StrutsXml> strutsXml;

    private Struts() {
    }

    /**
     * 单例模式初始化struts.xml，而不是每次跑runAction的时候都要初始化一次
     *
     * @return
     */
    public static Struts init() throws FileNotFoundException {

        if (instance == null) {
            /**
             * 0. 读取配置文件struts.xml
             */
            //创建SAXReader对象
            SAXReader reader = new SAXReader();
            //读取文件 转换成Document
            Document document = null;
            try {
                document = reader.read(new File("src/com/coding/coderising/litestruts/struts.xml"));
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            //获取根节点元素对象
            Element root = document.getRootElement();
            if ("struts".equals(root.getName())) {
                strutsXml = new HashMap<String, StrutsXml>();

                Iterator<Element> actions = root.elementIterator();
                while (actions.hasNext()) {
                    Element action = actions.next();
                    List<Attribute> attrList = action.attributes();

                    String actionName = null;
                    StrutsXml xml = null;
                    if (!"action".equals(action.getName())) {
                        continue;
                    }
                    //遍历属性节点
                    for (Attribute attribute : attrList) {
                        xml = new StrutsXml();
                        if ("name".equals(attribute.getName())) {
                            actionName = attribute.getValue();
                        }
                        if ("class".equals(attribute.getName())) {
                            xml.setClazz(attribute.getValue());
                            //获取result信息
                            Iterator<Element> results = action.elementIterator();
                            while (results.hasNext()) {
                                Element result = results.next();
                                List<Attribute> resultList = result.attributes();
                                for (Attribute resultAttr : resultList) {
                                    //System.out.println(resultAttr.getValue() + "  ,"+result.getText());
                                    xml.getResult().put(resultAttr.getValue(), result.getText());
                                }
                            }

                        }
                        //System.out.println("属性"+attribute.getName() +":" + attribute.getValue());
                    }

                    strutsXml.put(actionName, xml);
                }
            } else {
                throw new FileNotFoundException("not a struts XML file !");
            }


            instance = new Struts();
        }
        return instance;
    }

    public static View runAction(String actionName, Map<String, String> parameters) {

        if (instance == null) return null;
        if (actionName == null || "".equals(actionName.trim())) return null;
        View view = new View();
        StrutsXml struts = strutsXml.get(actionName);

        Class clazz = null;
        /**
         * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
         * 据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
         * ("name"="test" ,  "password"="1234") ,那就应该调用 setName和setPassword方法
         */
        //获取相应处理的action
        if (struts != null) {
            String className = struts.getClazz();
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new NullPointerException("action not found in struts file !");
        }

        if (clazz != null) {
            Object action = null;
            try {
                action = clazz.newInstance();

                //反射调用设置参数
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String para = entry.getKey();
                    if (!checkField(clazz, para)) continue;
                    //根据习惯，类的属性首字母在调用时大写，例如属性名是 age，则类方法为getAge
                    PropertyDescriptor pd = new PropertyDescriptor(para, clazz);

                    Method setMethod = pd.getWriteMethod();//获得set方法

                    setMethod.invoke(action, entry.getValue());

                }
                /**
                 * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
                 */
                //执行execute()
                Method excuteMethod = clazz.getDeclaredMethod("execute");
                String result = (String) excuteMethod.invoke(action);
                //通过xml文件获取返回值
                String jsp = struts.getResult().get(result);

                if (jsp == null || jsp.trim().equals("")) {
                    throw new NullPointerException("the requested file is not found !");
                }
                /**
                 * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
                 * 通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
                 * 放到View对象的parameters
                 */
                //执行get方法
                Map<String, String> viewMap = new HashMap<>();
                Field[] fields = clazz.getDeclaredFields();//获得属性

                for (Field field : fields) {
                    String getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method getMethod = clazz.getDeclaredMethod(getMethodName);
                    String returnVal = (String) getMethod.invoke(action);
                    viewMap.put(field.getName(), returnVal);
                }
                /**
                 * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
                 *    放到View对象的jsp字段中。
                 */
                view.setJsp(jsp);
                view.setParameters(viewMap);

            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        return view;


    }

    private static boolean checkField(Class clazz, String fieldName) {
        if (fieldName == null || fieldName.trim().equals("")) return false;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (fieldName.equals(field.getName())) return true;
        }
        return false;
    }


    public static void main(String args[]) {
        try {
            Struts.init();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, String> paras = new HashMap<>();
        paras.put("name", "test");
        paras.put("password", "1234");
        View view = Struts.runAction("login", paras);
    }
}

class StrutsXml {
    private String actionName;
    private String clazz;
    private Map<String, String> result = new HashMap<>();

    public StrutsXml(String actionName, String clazz, Map<String, String> result) {
        this.actionName = actionName;
        this.clazz = clazz;
        this.result = result;
    }

    public StrutsXml() {
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }
}