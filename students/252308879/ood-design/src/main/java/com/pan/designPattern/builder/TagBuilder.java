package com.pan.designPattern.builder;

import java.util.List;

public class TagBuilder {

	/**
	 * 根节点
	 */
	private TagNode rootNode;
	/**
	 * 当前节点
	 */
	private TagNode currentTagNode;
	/**
	 * 当前节点的父节点
	 */
	private TagNode currentTagNodeParent;

	public TagBuilder(String rootTagName){
		rootNode = new TagNode(rootTagName);
	}
	
	public TagBuilder addChild(String childTagName){
		List<TagNode> lastChildTagNodes = rootNode.getChildren();
		TagNode childTagNode = rootNode;
		while (lastChildTagNodes.size()  > 0){
			childTagNode = lastChildTagNodes.get(0);
			lastChildTagNodes = childTagNode.getChildren();
		}
		currentTagNode = new TagNode(childTagName);
		lastChildTagNodes.add(currentTagNode);
		currentTagNodeParent = childTagNode;
		return this;
	}

	public TagBuilder addSibling(String siblingTagName){
		currentTagNode = new TagNode(siblingTagName);
		if (currentTagNodeParent == null){
			rootNode.add(currentTagNode);
		}else {
			currentTagNodeParent.add(currentTagNode);
		}
		return this;
		
	}
	public TagBuilder setAttribute(String name, String value){
		currentTagNode.setAttribute(name, value);
		return this;
	}
	public TagBuilder setText(String value){
		
		return null;
	}
	public String toXML(){
		if (this.rootNode == null){
			return "";
		}
		return this.rootNode.toXML();
	}
}
