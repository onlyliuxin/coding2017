package com.donaldy.jvm.constant;

public class UTF8Info extends ConstantInfo{
	private int type = ConstantInfo.UTF8_INFO;
	private int length ;
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
		return "UTF8Info [type=" + type + ", length=" + length + ", value=" + value +")]";
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
