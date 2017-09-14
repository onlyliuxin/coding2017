package com.coderising.dp.builder;

public class TagBuilder {

	private TagNode root;
	private TagNode currentNode;
	private TagNode currentNodeParent;

	public TagBuilder(String rootTagName) {
		this.root = new TagNode(rootTagName);
		this.currentNode = this.root;
	}

	public TagBuilder addChild(String childTagName) {
		TagNode node = new TagNode(childTagName);
		this.currentNode.add(node);
		currentNodeParent = currentNode;
		currentNode = node;
		return this;
	}

	public TagBuilder addSibling(String siblingTagName) {
		TagNode node = new TagNode(siblingTagName);
		this.currentNodeParent.add(node);
		currentNode = node;
		return this;

	}

	public TagBuilder setAttribute(String name, String value) {
		this.currentNode.setAttribute(name, value);
		return this;
	}

	public TagBuilder setText(String value) {
		this.currentNode.setValue(value);
		return this;
	}

	public String toXML() {
		return this.root.toXML();
	}
}
