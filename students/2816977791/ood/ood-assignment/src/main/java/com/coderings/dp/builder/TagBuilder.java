package com.coderings.dp.builder;

public class TagBuilder {

    final TagNode rootNode;

    TagNode iteratorNode;

    TagNode prevIteratorNode;

    public TagBuilder(String rootTagName) {
        rootNode = new TagNode(rootTagName);
        iteratorNode = rootNode;
        prevIteratorNode = rootNode;
    }

    public TagBuilder addChild(String childTagName) {
        TagNode tagNode = new TagNode(childTagName);
        iteratorNode.add(tagNode);

        prevIteratorNode = iteratorNode;
        iteratorNode = tagNode;

        return this;
    }

    public TagBuilder addSibling(String siblingTagName) {
        TagNode tagNode = new TagNode(siblingTagName);
        prevIteratorNode.add(tagNode);

        iteratorNode = tagNode;

        return this;
    }

    public TagBuilder setAttribute(String name, String value) {
        iteratorNode.setAttribute(name, value);
        return this;
    }

    public TagBuilder setText(String value) {
        iteratorNode.setValue(value);
        return this;
    }

    public String toXML() {
        return rootNode.toXML();
    }
}
