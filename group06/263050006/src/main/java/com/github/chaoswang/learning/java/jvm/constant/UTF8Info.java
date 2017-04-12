package com.github.chaoswang.learning.java.jvm.constant;

/**
 * CONSTANT_Utf8_info { 
 * u1 tag; 
 * u2 length; 
 * u1 bytes[length]; 
 * }
 */
public class UTF8Info extends ConstantInfo {
	
	private int type = ConstantInfo.UTF8_INFO;
	private int length;
	private String value;

	public UTF8Info(ConstantPool pool) {
		super(pool);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getType() {
		return type;
	}

	@Override
	public String toString() {
		return "UTF8Info [type=" + type + ", length=" + length + ", value=" + value + ")]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}