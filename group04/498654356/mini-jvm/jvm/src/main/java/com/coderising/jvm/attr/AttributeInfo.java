package com.coderising.jvm.attr;

public class AttributeInfo {
	public static final String ATTR_CODE = "Code";
	public static final String ATTR_LINE_NUMBER_TABLE = "LineNumberTable";
	public static final String ATTR_LOCAL_VARIABLE_TABLE = "LocalVariableTable";
	
	private int attrNameIndex;
	private int attrLength;
	public int getAttrNameIndex() {
		return attrNameIndex;
	}
	public void setAttrNameIndex(int attrNameIndex) {
		this.attrNameIndex = attrNameIndex;
	}
	public int getAttrLength() {
		return attrLength;
	}
	public void setAttrLength(int attrLength) {
		this.attrLength = attrLength;
	}
	public AttributeInfo(int attrNameIndex, int attrLength) {
		super();
		this.attrNameIndex = attrNameIndex;
		this.attrLength = attrLength;
	}
	
}
