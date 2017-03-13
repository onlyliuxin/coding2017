package com.coderising.litestruts.parser;

import com.alibaba.fastjson.JSON;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * 解析 struts.xml 文件
 * @apiNote 借鉴 http://www.everycoding.com/coding/78.html; http://blog.csdn.net/caihaijiang/article/details/5944955
 * Created by luoziyihao on 3/5/17.
 */
public class DefaultStrutsParser implements StrutsParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public StrutsConfig parser(String filePathInClasspath) {
        String path = this.getClass().getClassLoader().getResource(filePathInClasspath).getPath();
        File input = new File(path);
        Digester digester = new Digester();
        // 创建 StrutsConfig 对象
        digester.addObjectCreate("struts", StrutsConfig.class);
        // 将 struts 节点上的attribute属性映射到 StrutsConfig 对象的属性上
        digester.addSetProperties("struts");
        digester.addObjectCreate("struts/action", ActionConfig.class);
        // 将 struts/action 节点上的attribute属性映射到 Action 对象的属性上, 并自定义属性映射
        digester.addSetProperties("struts/action"
                , new String[]{"name", "class"}, new String[]{"name", "className"});
        digester.addObjectCreate("struts/action/result", Result.class);
        digester.addSetProperties("struts/action/result"
                , new String[]{"name"}, new String[]{"name"});
        // 将 struts/action/result 节点上的body属性映射到 Result 对象的属性上
        digester.addCallMethod("struts/action/result", "setView", 0);
        // 对应struts/action/result 生成的对象添加到 Action中
        digester.addSetNext("struts/action/result", "addResult");
        // 对应struts/action 生成的对象添加到 Struts中
        digester.addSetNext("struts/action", "addAction");

        try {
            StrutsConfig strutsConfig = (StrutsConfig) digester.parse(input);
            logger.debug("strutsConfig={}", JSON.toJSONString(strutsConfig));
            return strutsConfig;
        } catch (IOException | SAXException e) {
            throw new IllegalStateException(e);
        }

    }
}
