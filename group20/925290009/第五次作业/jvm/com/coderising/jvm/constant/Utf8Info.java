package com.coderising.jvm.constant;

public class Utf8Info extends ConstantInfo{

	private int tag = ConstantInfo.UTF8_INFO;
	private int length;
	private String value;
	
	public Utf8Info(ConstantPool constantPool){
		super(constantPool);
	}
	public String getValue() {
		
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public int getType() {
		return tag;
	}

}
