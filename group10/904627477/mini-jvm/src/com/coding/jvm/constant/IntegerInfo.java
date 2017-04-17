package com.coding.jvm.constant;

public class IntegerInfo extends ConstantInfo {
	
	private int type = ConstantInfo.INTERGER_INFO;
	private int value;
	

	public IntegerInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public int getType() {
		return type;
	}

}
