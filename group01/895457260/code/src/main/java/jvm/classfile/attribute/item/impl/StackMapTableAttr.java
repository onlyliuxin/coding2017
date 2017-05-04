package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

public class StackMapTableAttr extends AttributeInfo {
	private String originalCode;

	public StackMapTableAttr(int attrNameIndex, int attrLen, String originalCode) {
		super(attrNameIndex, attrLen);
		this.originalCode = originalCode;
	}
}
