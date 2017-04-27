package com.coderising.jvm.constant;

public class NullConstantInfo extends ConstantInfo{

	@Override
	public int getType() {
		return -1;
	}
	@Override
	public void print() {
		System.out.println("NullConstantInfo");
	}

}
