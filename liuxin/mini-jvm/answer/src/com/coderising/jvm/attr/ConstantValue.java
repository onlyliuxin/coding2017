package com.coderising.jvm.attr;

public class ConstantValue extends AttributeInfo {

	private int constValueIndex;
	
	public ConstantValue(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	public int getConstValueIndex() {
		return constValueIndex;
	}
	public void setConstValueIndex(int constValueIndex) {
		this.constValueIndex = constValueIndex;
	}
	
	

}
