package com.coderising.dp.week1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TagNode {
    private String tagName;
    private String tagValue;
    private List<TagNode> children = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();

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
        String name;
        String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        String lineBreaker = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(this.tagName);
        if (!isEmpty(this.attributes)) {
            attributes.forEach(attribute -> sb.append(" ")
                    .append(attribute.getName())
                    .append("=\"")
                    .append(attribute.getValue())
                    .append("\""));
        }
        sb.append(">");
        if (!isEmpty(this.children)) {
            sb.append(lineBreaker);
            children.forEach(child -> sb.append(child.toString()));
        }
        sb.append(lineBreaker).append("</").append(this.tagName).append(">").append(lineBreaker);
        return sb.toString();

    }

    private boolean isEmpty(Collection<?> c) {
        return c == null || c.size() == 0;
    }
}
