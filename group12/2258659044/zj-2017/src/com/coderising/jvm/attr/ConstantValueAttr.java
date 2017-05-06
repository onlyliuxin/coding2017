package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

public class ConstantValueAttr extends AttributeInfo{

	private int constantValueIndex;
		
	public int getConstantValueIndex() {
		return constantValueIndex;
	}

	public void setConstantValueIndex(int constantValueIndex) {
		this.constantValueIndex = constantValueIndex;
	}

	public ConstantValueAttr(int attrNameIndex, int attrLen,int constantValueIndex) {
		super(attrNameIndex, attrLen);
		this.constantValueIndex = constantValueIndex;
	}

	public static ConstantValueAttr parse(ByteCodeIterator iter){
		
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int constantValueIndex = iter.nextU2ToInt();
		return new ConstantValueAttr(attrNameIndex,attrLen,constantValueIndex);
	}
	
}
