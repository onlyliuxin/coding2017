package com.coding.jvm.constant;

public class FloatInfo extends ConstantInfo {
	
	private int type = ConstantInfo.FLOAT_INFO;
	private float value;
	

	public FloatInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}


	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}


	public int getType() {
		return type;
	}

}
