package com.coderising.litestruts.builder;

import com.coderising.litestruts.conf.StrutsConfiguration;
import com.coderising.litestruts.exception.BuilderException;
import com.coderising.litestruts.model.Response;
import com.coderising.litestruts.parser.xml.XNode;
import com.coderising.litestruts.parser.xml.XPathParser;

import java.io.InputStream;
import java.util.*;

/**
 * Created by huitailang on 17/3/5.
 * @author zhangkun
 * @date 2017年03月05日17:45:54
 * litestruts配置构建器
 */
public class StrutsConfigBuilder {
    private XPathParser parser;
    private boolean parsed;

    public StrutsConfigBuilder(String xml){
        this(new XPathParser(xml, false));
    }

    public StrutsConfigBuilder(InputStream inputStream) {
        this(new XPathParser(inputStream));
    }

    private StrutsConfigBuilder(XPathParser parser) {
        this.parser = parser;
        this.parsed = false;
    }

    public StrutsConfiguration parse() {
        if (parsed) {
            throw new BuilderException("Each StrutsConfigBuilder can only be used once.");
        }
        this.parsed = true;
        StrutsConfiguration configuration = new StrutsConfiguration();

        try {
            //获取action个数
            int actionSize = parser.evalNodes("/struts/*").size();
            Collection<Map<String, String>> actionMap = new HashSet<>();
            Collection<Map<String, Set<Response>>> responseMap = new HashSet<>();

            for (int i = 0; i < actionSize; i++) {
                XNode actionNode = parser.evalNodes("/struts/*").get(i);
                String actionName = actionNode.evalString("/struts/action/@name");
                String actionClass = actionNode.evalString("/struts/action/@class");
                Map<String, String> action = new HashMap<>();
                action.put(actionName, actionClass);
                actionMap.add(action);

                int resultSize = actionNode.evalNodes("/*").size();

                Map<String, Set<Response>> actionResponse = new HashMap<>();
                Set<Response> responseSet = new HashSet<>();
                for (int j = 0; j < resultSize; j++) {
                    XNode resultNode = actionNode.evalNodes("/*").get(j);
                    String resultCode = resultNode.evalString("/struts/action/result/@name");
                    String resultViewPath = resultNode.evalString("/struts/action/result");
                    Response response = new Response(resultCode, resultViewPath);
                    responseSet.add(response);
                }
                actionResponse.put(actionClass, responseSet);
                responseMap.add(actionResponse);
            }

            configuration.setActionMap(actionMap);
            configuration.setResponseMap(responseMap);
        } catch (Exception e) {
            throw new BuilderException("Error parsing LiteStruts Configuration. Cause: " + e, e);
        }

        return configuration;
    }
}