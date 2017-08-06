package MultiLevelNesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Tag节点
 *
 * @author LanyuanXiaoyao
 * @create 2017-07-18
 */
public class TagNode {
    private String tagName;
    private String tagValue;
    private List<TagNode> children = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();

    public TagNode(String tagName) {
        this.tagName = tagName;
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

    public static class Attribute {
        public Attribute(String name, String value) {
            this.name = name;
            this.value = value;
        }

        String name;
        String value;
    }

    public String toXML() {
        return toXML(this);
    }

    private String toXML(TagNode node) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<").append(node.tagName);
        if (node.attributes.size() > 0) {
            for (int i = 0; i < node.attributes.size(); i++) {
                Attribute attr = node.attributes.get(i);
                buffer.append(" ").append(toXML(attr));
            }
        }
        if (node.children.size() == 0) {
            buffer.append("/>");
            return buffer.toString();
        }
        buffer.append(">");
        for (TagNode childNode : node.children) {
            buffer.append(toXML(childNode));
        }
        buffer.append("</").append(node.tagName).append(">");

        return buffer.toString();
    }

    private String toXML(Attribute attr) {
        return attr.name + "=\"" + attr.value + "\"";
    }
}
