package com.coderising.dp.builder;

public class TagBuilder {
    TagNode root;
    TagNode parNode;
    TagNode curNode;


    public TagBuilder(String rootTagName) {
        root = new TagNode(rootTagName);
        curNode = root;
        parNode = root;
    }

    public TagBuilder addChild(String childTagName) {
        TagNode node = new TagNode(childTagName);
        curNode.add(node);
        parNode = curNode;
        curNode = node;
        return this;
    }

    public TagBuilder addSibling(String siblingTagName) {
        TagNode node = new TagNode(siblingTagName);
        parNode.add(node);
        parNode = curNode;
        curNode = node;
        return this;

    }

    public TagBuilder setAttribute(String name, String value) {
        curNode.setAttribute(name, value);
        return this;
    }

    public TagBuilder setText(String value) {
        curNode.setValue(value);
        return this;
    }

    public String toXML() {
        return root.toXML();
    }

}
