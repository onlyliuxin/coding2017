package com.coderising.dp.builder;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
	private String tagName;	
	private String tagValue;
	private List<TagNode> children = new ArrayList<>();	
	private List<Attribute> attributes = new ArrayList<>();
	
	public TagNode(String name){
		this.tagName = name;
	}
	public void add(TagNode child){
		this.children.add(child);
	}
	public void setAttribute(String name, String value) {
		Attribute attr = findAttribute(name);
		if(attr != null){
			attr.value = value;
		} else {
			attributes.add(new Attribute(name,value));
		}
	}
	private Attribute findAttribute(String name){
		for(Attribute attr : attributes){
			if(attr.name.equals(name)){
				return attr;
			}
		}
		return null;
	}
	public void setValue(String value) {
		this.tagValue = value;
		
	}
	public String getTagName() {
		return tagName;
	}
	public List<TagNode> getChildren() {
		return children;
	}
	
	private static class Attribute{
		public Attribute(String name, String value) {
			this.name = name;
			this.value = value;
		}
		String name;
		String value;
		
	}
	public String toXML(){
		return toXML(this);
	}
	private String toXML(TagNode node){
		StringBuilder buffer = new StringBuilder();
		buffer.append("<").append(node.tagName);
		if(node.attributes.size()> 0){
			for(int i=0;i<node.attributes.size();i++){
				Attribute attr = node.attributes.get(i);
				buffer.append(" ").append(toXML(attr));				
			}
		}
		if(node.children.size()== 0){
			buffer.append("/>");
			return buffer.toString();
		}
		buffer.append(">");		
		for(TagNode childNode : node.children){
			buffer.append(toXML(childNode));
		}
		buffer.append("</").append(node.tagName).append(">");
		
		
		return buffer.toString();
	}
	private String toXML(Attribute attr){
		return attr.name+"=\""+attr.value + "\"";
	}
}
