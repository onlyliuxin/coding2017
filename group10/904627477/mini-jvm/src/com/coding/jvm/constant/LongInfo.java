package com.coding.jvm.constant;

public class LongInfo extends ConstantInfo {
	
	private int type = ConstantInfo.LONG_INFO;
	private long value;
	

	public LongInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}


	public long getValue() {
		return value;
	}


	public void setValue(long value) {
		this.value = value;
	}


	public int getType() {
		return type;
	}

}
