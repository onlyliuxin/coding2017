package com.github.HarryHook.coding2017.litestruts;

import java.lang.reflect.Method;
import java.util.Map;

public class Struts {

    private final static Configuration cfg = new Configuration("struts.xml");

    public static View runAction(String actionName, Map<String, String> parameters) {

	String clzName = cfg.getClassName(actionName);

	if (clzName == null) {

	    return null;
	}

	try {

	    Class<?> clz = Class.forName(clzName);
	    Object action = clz.newInstance();

	    ReflectionUtil.setParameters(action, parameters);

	    Method m = clz.getDeclaredMethod("execute");
	    String resultName = (String) m.invoke(action);

	    Map<String, Object> params = ReflectionUtil.getParameterMap(action);
	    String resultView = cfg.getResultView(actionName, resultName);

	    View view = new View();
	    view.setParameters(params);
	    view.setJsp(resultView);
	    return view;

	} catch (Exception e) {

	    e.printStackTrace();
	}

	return null;

    }

}