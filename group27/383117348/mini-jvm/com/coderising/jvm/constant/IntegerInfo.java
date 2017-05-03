package com.coderising.jvm.constant;

public class IntegerInfo extends ConstantInfo {
	
	private int type = ConstantInfo.INTEGER_INFO;
	
	private int value;
	
	public IntegerInfo(ConstantPool pool) {
		super(pool);
	}

	@Override
	public int getType() {
		
		return type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
