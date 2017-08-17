package com.coderising.dp.builder;

public class TagBuilder {
	private TagNode rootNode = null;
	private TagNode curNode = null;
	private TagNode parentNode = null;
	
	public TagBuilder(String rootTagName){
		rootNode = new TagNode(rootTagName);
		curNode = rootNode;
	}
	
	public TagBuilder addChild(String childTagName){
		TagNode tn = new TagNode(childTagName);
		curNode.add(tn);
		parentNode = curNode;
		curNode = tn;
		
		return this;
	}
	public TagBuilder addSibling(String siblingTagName){
		TagNode tn = new TagNode(siblingTagName);
		parentNode.add(tn);
		curNode = tn;
		
		return this;
	}
	public TagBuilder setAttribute(String name, String value){
		curNode.setAttribute(name, value);
		
		return this;
	}
	public TagBuilder setText(String value){
		curNode.setValue(value);
		
		return this;
	}
	public String toXML(){
		return rootNode.toXML();
	}
}
