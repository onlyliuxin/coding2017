package com.coderising.jvm.constant;

public class UTF8Info extends ConstantInfo {
	private int type = UTF8_INFO;//u1 tag
	private int length;//u2 length
	private String value;//n 个 u1
	public UTF8Info(ConstantPool pool) {
		super(pool);
	}
	@Override
	public int getType() {
		return type;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "UTF8Info [type="+type+",length="+length+",value="+value+"]";
	}

}
