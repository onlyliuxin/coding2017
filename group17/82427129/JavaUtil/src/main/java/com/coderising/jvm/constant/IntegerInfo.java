package com.coderising.jvm.constant;

public class IntegerInfo extends ConstantInfo {
	private int type = INTEGER_INFO;
	private int Num;

	@Override
	public int getType() {
		return type;
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

	public int getNum() {
		return Num;
	}

	public void setNum(int num) {
		Num = num;
	}
	
}
