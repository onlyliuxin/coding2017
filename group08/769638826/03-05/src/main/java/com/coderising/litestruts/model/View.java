package com.coderising.litestruts.model;

import java.util.Map;

/**
 * Created by huitailang on 17/3/4.
 * @author zhangkun
 * @date 2017年03月05日17:47:05
 * 视图类
 */
public class View {
    private String jsp;//请求响应jsp页面
    private Map parameters;//响应数据model

    public String getJsp() {
        return jsp;
    }

    public View setJsp(String jsp) {
        this.jsp = jsp;
        return this;
    }

    public Map getParameters() {
        return parameters;
    }

    public View setParameters(Map parameters) {
        this.parameters = parameters;
        return this;
    }
}
