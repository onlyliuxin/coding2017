package com.java.xiaoqin.litestruts.manager;

import com.java.xiaoqin.litestruts.bean.ActionBean;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by xiaoqin on 17-3-5.
 */
public class StrutsParseManager {

    private final static String ACTION = "action";
    private final static String ACTION_NAME = "name";
    private final static String ACTION_CLASS = "class";
    private static final String RESULT_NAME = "name";

    private final static StrutsParseManager INSTANCE = new StrutsParseManager();


    private Map<String, ActionBean> mActionBeanMap = new HashMap<>();

    private StrutsParseManager() {
    }

    public static StrutsParseManager getInstance() {
        return INSTANCE;
    }

    public void init(String path) {
        if (!Objects.isNull(path) && !"".equals(path)) {
            parseXML(path);
        } else {
            throw new NullPointerException("path is null");
        }
    }

    private void parseXML(String path) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(path));
            Element rootElement = document.getRootElement();
            Iterator actionIterator = rootElement.elementIterator(ACTION);
            while (actionIterator.hasNext()) {
                Object actionObj = actionIterator.next();
                if (actionObj instanceof Element) {
                    String name = ((Element) actionObj).attributeValue(ACTION_NAME);
                    String className = ((Element) actionObj).attributeValue(ACTION_CLASS);
                    ActionBean actionBean = new ActionBean();
                    actionBean.setClassName(className);
                    mActionBeanMap.put(name, actionBean);
                    Iterator iteratorResult = ((Element) actionObj).elementIterator();
                    while (iteratorResult.hasNext()) {
                        Object resultObj = iteratorResult.next();
                        if (resultObj instanceof Element) {
                            String resultName = ((Element) resultObj).attributeValue(RESULT_NAME);
                            String resultText = ((Element) resultObj).getText();
                            actionBean.addResult(resultName, resultText);
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public String findClassNameByActionName(String actionName) {
        String className = "";
        if (mActionBeanMap.containsKey(actionName)) {
            className = mActionBeanMap.get(actionName).getClassName();
        }
        return className;
    }

    public String getResult(String actionName, String result) {
        String resultResult = "";
        if (mActionBeanMap.containsKey(actionName)) {
            Map<String, String> actionResult = mActionBeanMap.get(actionName).getResult();
            if (null != actionResult && actionResult.containsKey(result)) {
                resultResult = actionResult.get(result);
            }

        }
        return resultResult;
    }
}
