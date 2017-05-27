package com.coderising.jvm.constant;

public class StringInfo extends ConstantInfo {
	private int stringIndex;

	public StringInfo(int stringIndex) {
		super();
		this.stringIndex = stringIndex;
	}

	public int getStringIndex() {
		return stringIndex;
	}
	
}
