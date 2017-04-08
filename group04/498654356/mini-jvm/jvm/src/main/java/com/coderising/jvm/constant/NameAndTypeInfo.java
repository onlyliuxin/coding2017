package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo {
	private int index1;
	private int index2;
	public NameAndTypeInfo(int index1, int index2) {
		super();
		this.index1 = index1;
		this.index2 = index2;
	}
	public int getIndex1() {
		return index1;
	}
	public int getIndex2() {
		return index2;
	}

}
