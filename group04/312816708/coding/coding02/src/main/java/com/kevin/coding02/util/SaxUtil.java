package com.kevin.coding02.util;

import com.kevin.coding02.model.ActionModel;
import com.kevin.coding02.model.ResultModel;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YinWenBing on 2017/2/28.
 */
public class SaxUtil extends DefaultHandler {
    private List<ActionModel> actions;
    private ActionModel action;
    private List<ResultModel> results;
    private ResultModel result;

    private String nodeName;


    /**
     * 开始解析文档
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        actions = new ArrayList<ActionModel>();
    }

    /**
     * 解析开始节点
     *
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("action".equals(qName)) {
            action = new ActionModel();
            results = new ArrayList<ResultModel>();
            //获取action节点的name属性
            action.setActionName(String.valueOf(attributes.getValue(0)));
            //获取action节点的class属性
            action.setActionClass(String.valueOf(attributes.getValue(1)));
        }

        if ("result".equals(qName)) {
            result = new ResultModel();
            //获取result节点的name属性
            result.setName(String.valueOf(attributes.getValue(0)));
        }
        nodeName = qName;
    }

    /**
     * 获取节点内容
     *
     * @param ch
     * @param start
     * @param length
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (null != nodeName) {
            String content = new String(ch, start, length);
            if ("result".equals(nodeName)) {
                result.setValue(content);
            }
        }
    }

    /**
     * 解析结束节点
     *
     * @param uri
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("result".equals(qName)) {
            results.add(result);
            result = null;
        }

        if ("action".equals(qName)) {
            action.setResults(results);
            actions.add(action);
            action = null;
        }
        nodeName = null;
    }

    /**
     * 结束遍历文档
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    /**
     * 返回解析结果
     */
    public List<ActionModel> getActions() throws Exception {
        return actions;
    }

}
