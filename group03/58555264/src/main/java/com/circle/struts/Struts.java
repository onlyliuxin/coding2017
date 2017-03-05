package com.circle.struts;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keweiyang on 2017/3/2.
 */
public class Struts {

    private final static String D = "1234";

    private List<ActionEntity> actionEntityList = new ArrayList<>();

    private ActionEntity actionEntity = null;

    /**
     * @param actionName action方法名称 例如：LoginAction
     * @param parameters 参数map,例如：name->liuxin,password->1234
     * @param pathName   文件路径
     * @return
     */
    public View runAction(String actionName, Map<String, String> parameters, String pathName) {
        /**
         * 0.读取配置文件struts.xml
         *
         * 1.根据actionName找到对应的class，例如LoginActon,通过反射实例化（创建对象），然后根据parameters中的数据，调用
         * 对象的setter方法，例如parameters中的数据是（"name"="liuxin","password"="1234"）,那就调用setName和setPassword方法
         *
         * 2.通过反射调用对象的execute方法，并获得返回值，例如："success"
         *
         * 3. 通过反射找到对象的所有getter方法（例如getMessage）,通过反射调用，把值和属性形成一个HashMap,例如{"message":"登录成功"}，
         * 放到View对象的parameters
         *
         * 4. 根据struts.xml中的<result>配置，以及execute的返回值，确定哪一个jsp，放到View对象的jsp对象中
         *
         */

        try {
            //1:读取struts.xml
            this.getActionEntityList(pathName);
            //2:获取对应的classpath
            String classPath = initParameter(actionName, parameters);
            //3:反射
            View view = createClass(classPath, parameters);
            return view;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 将map中的key值修改为setxxx，并且获取classpath
     *
     * @param actionName
     * @param mapParameters
     * @return
     */
    private String initParameter(String actionName, Map<String, String> mapParameters) {
        if (actionName == null || actionName.trim().length() == 0) {

            throw new IllegalStateException("actionName为空");
        }
        if (mapParameters == null || mapParameters.size() == 0) {
            throw new IllegalStateException("参数为空");
        }

        String classpath = "";
        for (ActionEntity entity : this.actionEntityList) {
            if (entity.getName().equals(actionName)) {
                this.actionEntity = entity;
                classpath = entity.getClassName();
                break;

            }
        }


        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, String> entry : mapParameters.entrySet()) {
            String methodName = "set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
            map.put(methodName, entry.getValue());
        }

        mapParameters.clear();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            mapParameters.put(entry.getKey(), entry.getValue());
        }


        return classpath;
    }

    private View createClass(String classpath, Map<String, String> mapParameters) throws Exception {
        View view = null;
        if (classpath.equals("")) {

            throw new IllegalStateException("classPath为空");
        }

        Class<?> clazz = Class.forName(classpath);
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        Field[] fields = clazz.getDeclaredFields();

        //  先调用setter方法
        for (Map.Entry<String, String> entry : mapParameters.entrySet()) {
            Method method = clazz.getDeclaredMethod(entry.getKey(), String.class);
            if (method != null) {
                method.invoke(obj, entry.getValue());
            }

        }

        // 再调用execute方法
//        if (obj instanceof LoginAction) {
        Method method = clazz.getDeclaredMethod("execute");
        String ret = (String) method.invoke(obj, null);

        Map<String, String> retMap = this.actionEntity.getResultMap();
        for (Map.Entry<String, String> result : retMap.entrySet()) {
            if (ret.equals(result.getKey())) {
                Map<String, String> map = new HashMap<>();
                System.out.println(result.getValue());

                this.showView(methods, fields, obj, clazz, map);
                view = new View();
                view.setJsp(result.getValue());
                view.setParameters(map);
                System.out.println(view.toString());
                break;
            }
//            }

        }
        return view;
    }

    private void showView(Method[] methods, Field[] fields, Object object, Class<?> clazz, Map<String, String> map) throws Exception {


        String[] ss = new String[fields.length];
        Method[] ms = new Method[methods.length];
        for (int i = 0; i < fields.length; i++) {
            ss[i] = fields[i].getName();
        }

        for (int i = 0; i < ss.length; i++) {
            String str = ss[i];
            ss[i] = "get" + ss[i].substring(0, 1).toUpperCase() + ss[i].substring(1);


            ms[i] = clazz.getDeclaredMethod(ss[i], null);
            String returnType = (String) ms[i].invoke(object, null);
            map.put(str, returnType);
//            System.out.println(returnType.toString());
        }


    }

    /**
     * 读取指定路径
     *
     * @param pathName 文件路径
     * @return
     */
    private List<ActionEntity> getActionEntityList(String pathName) {
        //读取struts.xml配置文件
        if (pathName == null || pathName.trim().length() == 0) {
            throw new IllegalStateException("pathName为空");
        }
        Document document = XmlUtil.getDocument(pathName);
        Map<String, String> resultMap = null;

        if (document.hasChildNodes()) {
            NodeList nodeList = document.getElementsByTagName("struts");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element rootEle = (Element) nodeList.item(i);

                if (rootEle.hasChildNodes()) {
                    NodeList childList = rootEle.getElementsByTagName("action");

                    for (int j = 0; j < childList.getLength(); j++) {
                        Element childEle = (Element) childList.item(j);
                        ActionEntity actionEntity = new ActionEntity();

                        String name = childEle.getAttribute("name");
                        String className = childEle.getAttribute("class");
                        actionEntity.setClassName(className);
                        actionEntity.setName(name);

                        if (childEle.hasChildNodes()) {
                            NodeList grandSonList = childEle.getElementsByTagName("result");
                            resultMap = new HashMap<>();
                            for (int k = 0; k < grandSonList.getLength(); k++) {
                                Element resultEle = (Element) grandSonList.item(k);
                                String resultName = resultEle.getAttribute("name");
                                String value = resultEle.getTextContent();
                                resultMap.put(resultName, value);
                            }
                        }
                        actionEntity.setResultMap(resultMap);

                        actionEntityList.add(actionEntity);
                    }
                }
            }
        }
        return actionEntityList;
    }


}
