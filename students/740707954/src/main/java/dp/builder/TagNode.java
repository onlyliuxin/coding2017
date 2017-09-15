package dp.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 标签
 * Created by lx on 2017/7/22.
 */
public class TagNode {
    private String tagName;
    private String tagValue;
    private List<TagNode> children = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();

    public TagNode(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 添加子标签
     * @param node
     */
    public void add(TagNode node) {
        children.add(node);
    }

    /**
     * 设置属性
     * @param key
     * @param value
     */
    public void setAttribute(String key, String value) {
        Attribute attr = new Attribute();
        attr.setName(key);
        attr.setValue(value);
        attributes.add(attr);
    }

    /**
     * 查找当前标签属性
     * @param name
     * @return
     */
    private Attribute findAttribute(String name) {
        for (Attribute attr : attributes) {
            if (attr.getName().equals(name)) {
                return attr;
            }
        }
        return null;
    }

    /**
     * 转成xml字符串
     * @return
     */
    public String toXML() {
        return toXML(this);
    }

    /**
     * 将标签转成xml
     * @param node
     * @return
     */
    private String toXML(TagNode node) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<").append(node.tagName);
        if (node.attributes.size() > 0) {
            for (Attribute attr : node.attributes) {
                buffer.append(" ").append(toXML(attr));
            }
        }

        if (node.children.size() == 0) {
            buffer.append("/>");
            return buffer.toString();
        }

        buffer.append(">");
        for (TagNode childrenNode : node.children) {
            buffer.append(toXML(childrenNode));
        }

        buffer.append("</").append(node.tagName).append(">");
        return buffer.toString();
    }

    /**
     * 将属性转成xml
     * @param attr
     * @return
     */
    private String toXML(Attribute attr) {
        return attr.getName() + "=\"" + attr.getValue() + "\"";
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public List<TagNode> getChildren() {
        return children;
    }

    public void setChildren(List<TagNode> children) {
        this.children = children;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
