package com.coderising.litestruts.parser;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.coderising.litestruts.api.StructAction;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author haipop Date: 17-3-2 Time: 下午3:13
 */
public class StructXmlParser extends DefaultHandler {
    // 数据集合
    private List<StructAction> data;
    // 节点内的属性名称
    private String tagName;
    // 构建元素
    private StructAction action;
    // 当前key；
    private String key;

    @Override
    public void startDocument() throws SAXException {
        data = Lists.newArrayList();
    }

    /**
     * 调用多次 开始解析
     */
    @Override
    public void startElement(String uri, String localName, String eleNme, Attributes attributes) throws SAXException {
        if (eleNme.equals("action")) {
            this.action = new StructAction();
            this.action.setClazzName(attributes.getValue("class"));
            this.action.setName(attributes.getValue("name"));
        }
        if (eleNme.equals("result")) {
            if (this.action == null) {
                throw new RuntimeException("配置文件不合法");
            }
            key = attributes.getValue("name");
        }

        this.tagName = eleNme;
    }

    @Override
    public void endElement(String uri, String localName, String eleNme) throws SAXException {
        if (eleNme.equals("action")) {
            this.data.add(this.action);
        }
        this.tagName = eleNme;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (Objects.equals(this.tagName, "result")) {
            Map<String, String> actions = this.action.getActions();
            if (MapUtils.isEmpty(actions)) {
                actions = Maps.newHashMap();
            }
            String value = new String(ch, start, length);
            if (StringUtils.isNotBlank(value.trim())) {
                actions.put(this.key, value);
            }
            this.action.setActions(actions);
        }
    }

    public List<StructAction> getData() {
        return data;
    }

}