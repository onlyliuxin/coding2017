package com.coderising.jvm.constant;


public class FloatInfo extends ConstantInfo{
	private int type = ConstantInfo.FLOAT_INFO;
	
	private float value;
	
	public FloatInfo(ConstantPool pool){
		super(pool);
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
