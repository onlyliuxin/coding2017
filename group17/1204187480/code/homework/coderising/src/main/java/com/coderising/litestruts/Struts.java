package com.coderising.litestruts;

import com.coderising.litestruts.parser.ActionConfig;
import com.coderising.litestruts.parser.DefaultStrutsParser;
import com.coderising.litestruts.parser.StrutsConfig;
import com.coderising.litestruts.parser.StrutsParser;
import com.coding.common.util.BeanUtils;

import java.util.Map;


public class Struts {

    private static StrutsParser strutsParser = new DefaultStrutsParser();

    private static final String STRUTS_CONFIG_PATH = "struts.xml";
    private static final BeanUtils beanUtils = new BeanUtils();

    public static View runAction(String actionName, Map<String, String> parameters) {

        /*
         
		0. 读取配置文件struts.xml

 		1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
		据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是 
		("name"="test" ,  "password"="1234") ,     	
		那就应该调用 setName和setPassword方法
		
		2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
		
		3. 通过反射找到对象的所有getter方法（例如 getMessage）,  
		通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,  
		放到View对象的parameters
		
		4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，  
		放到View对象的jsp字段中。

        */

        /**
         * 0. 读取配置文件struts.xml
         */
        StrutsConfig strutsConfig = strutsParser.parser(STRUTS_CONFIG_PATH);
        ActionConfig actionConfig = strutsConfig.getActions().get(actionName);
        /**
         * 1. 根据actionName找到相对应的class ， 例如LoginAction,   通过反射实例化（创建对象）
         据parameters中的数据，调用对象的setter方法， 例如parameters中的数据是
         ("name"="test" ,  "password"="1234") ,
         那就应该调用 setName和setPassword方法
         */
        Object action = setPropertiesForAction(actionConfig, actionName, parameters);

        /**
         * 2. 通过反射调用对象的exectue 方法， 并获得返回值，例如"success"
         */
        String resultName = doExecute(action);
        /**
         * 3. 通过反射找到对象的所有getter方法（例如 getMessage）,
         通过反射来调用， 把值和属性形成一个HashMap , 例如 {"message":  "登录成功"} ,
         放到View对象的parameters
         */
        View view = createViewAndSetParameters(action);
        /**
         * 4. 根据struts.xml中的 <result> 配置,以及execute的返回值，  确定哪一个jsp，
         放到View对象的jsp字段中。
         */
        setViewValue(view, resultName, actionConfig);
        return view;
    }

    private static void setViewValue(View view, String resultName, ActionConfig config) {
        view.setJsp(config.getResults().get(resultName).getView());
    }

    private static View createViewAndSetParameters(Object action) {
        View view = new View();
        view.setParameters(beanUtils.describe(action));
        return view;
    }

    private static String doExecute(Object action) {
        return (String) beanUtils.invokeWithNoParamter("execute", action);
    }

    private static Object setPropertiesForAction(ActionConfig actionConfig, String actionName, Map<String, String> parameters) {
        Object action = createInstance(findActionClass(actionConfig.getClassName()));
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            setProperty(entry.getKey(), entry.getValue(), action);
        }
        return action;
    }

    /**
     * todo 校验 key 是否存在
     *
     * @param key
     * @param value
     * @param action
     */
    private static void setProperty(String key, String value, Object action) {
        beanUtils.setPara(value, key, action);
    }

    private static Object createInstance(Class classValue) {
        try {
            return classValue.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Class findActionClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

}
