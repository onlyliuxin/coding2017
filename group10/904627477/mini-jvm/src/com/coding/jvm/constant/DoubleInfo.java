package com.coding.jvm.constant;

public class DoubleInfo extends ConstantInfo {
	
	private int type = ConstantInfo.DOUBLE_INFO;
	private double value;
	

	public DoubleInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}


	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public int getType() {
		return type;
	}

}
