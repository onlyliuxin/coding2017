package com.coderising.litestruts.conf;

import com.coderising.litestruts.model.Response;

import java.util.*;

/**
 * Created by huitailang on 17/3/4.
 * @author  zhangkun
 * @date 2017年03月05日17:46:09
 * LiteStruts全局配置
 */
public class StrutsConfiguration {
    /**
     * 请求action到对应处理类的映射
     */
    private Collection<Map<String, String>> actionMap = new HashSet<Map<String, String>>();

    /**
     * 请求处理类所有响应结果集合
     */
    private Collection<Map<String, Set<Response>>> responseMap = new HashSet<Map<String, Set<Response>>>();

    public Collection<Map<String, String>> getActionMap() {
        return actionMap;
    }

    public void setActionMap(Collection<Map<String, String>> actionMap) {
        this.actionMap = actionMap;
    }

    public Collection<Map<String, Set<Response>>> getResponseMap() {
        return responseMap;
    }

    public void setResponseMap(Collection<Map<String, Set<Response>>> responseMap) {
        this.responseMap = responseMap;
    }
}
