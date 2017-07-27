package com.coderising.mydp.builder;

public class TagBuilder {
    private TagNode rootNode;
	private TagNode tagNode;
	private TagNode parentTagNode;
	public TagBuilder(String rootTagName){
		tagNode = new TagNode(rootTagName);
        rootNode = tagNode;
	}
	
	public TagBuilder addChild(String childTagName){
	    TagNode newTagNode = new TagNode(childTagName);
		tagNode.add(newTagNode);
		parentTagNode = tagNode;
		tagNode = newTagNode;
		return this;
	}
	public TagBuilder addSibling(String siblingTagName){
        TagNode newTagNode = new TagNode(siblingTagName);
        parentTagNode.add(newTagNode);
        tagNode = newTagNode;
		return this;
		
	}
	public TagBuilder setAttribute(String name, String value){
		tagNode.setAttribute(name, value);
		return this;
	}
	public TagBuilder setText(String value){
		tagNode.setValue(value);
		return this;
	}
	public String toXML(){
        return rootNode.toXML();
	}
}
