package com.github.chaoswang.learning.java.jvm.constant;
/**
 * CONSTANT_String_info { 
 * u1 tag; 
 * u2 string_index;
 */
public class StringInfo extends ConstantInfo {
	
	private int type = ConstantInfo.STRING_INFO;
	private int index;

	public StringInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String toString() {
		return this.getConstantPool().getUTF8String(index);
	}

}