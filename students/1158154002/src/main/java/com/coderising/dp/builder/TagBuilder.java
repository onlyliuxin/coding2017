package com.coderising.dp.builder;

import java.util.List;

public class TagBuilder {
	private TagNode root;
	private TagNode now;
	private TagNode prev;
	public TagBuilder(String rootTagName) {
		root=new TagNode(rootTagName);
		now=root;
	}

	public TagBuilder addChild(String childTagName) {
		prev=now;
		now=new TagNode(childTagName);
		prev.add(now);

		return this;
	}

	public TagBuilder addSibling(String siblingTagName) {
		List<TagNode> children=prev.getChildren();
		now=new TagNode(siblingTagName);
		children.add(now);
		return this;

	}

	public TagBuilder setAttribute(String name, String value) {
		now.setAttribute(name, value);
		return this;
	}

	public TagBuilder setText(String value) {

		return this;
	}

	public String toXML() {
		return root.toXML();
	}
}
