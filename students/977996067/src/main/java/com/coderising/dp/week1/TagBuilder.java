package com.coderising.dp.week1;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TagBuilder {

    private TagNode rootTag;

    private AtomicReference<TagNode> tempParentNode = new AtomicReference<>();

    private AtomicReference<TagNode> currentNode = new AtomicReference<>();

    public TagBuilder(String rootTagName) {
        this.rootTag = new TagNode();
        rootTag.setTagName(rootTagName);
        tempParentNode.set(rootTag);
        currentNode.set(rootTag);
    }

    public TagBuilder addChild(String childTagName) {
        TagNode node = currentNode.get();
        tempParentNode.set(node);
        currentNode.set(doAddChildren(node, childTagName));
        return this;
    }

    private TagNode doAddChildren(TagNode node, String childTagName) {
        List<TagNode> children = node.getChildren();
        TagNode childTag = new TagNode();
        childTag.setTagName(childTagName);
        children.add(childTag);
        return childTag;
    }

    public TagBuilder addSibling(String childTagName) {
        TagNode tagNode = tempParentNode.get();
        TagNode childTag = doAddChildren(tagNode, childTagName);
        currentNode.set(childTag);
        return this;
    }

    public TagBuilder setAttribute(String key, String value) {
        TagNode tagNode = currentNode.get();
        List<TagNode.Attribute> attributes = tagNode.getAttributes();

        TagNode.Attribute attribute = new TagNode.Attribute();
        attribute.setName(key);
        attribute.setValue(value);
        attributes.add(attribute);
        return this;
    }

    public String toXML() {
        return rootTag.toString();
    }
}


// ~ HomeWork2
// ========================================================================================================

// 单例的类: java.lang.Runtime