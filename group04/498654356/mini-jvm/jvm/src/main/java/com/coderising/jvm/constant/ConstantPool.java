package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	public static final int C_UTF8_INFO = 1;
	public static final int C_CLASS_INFO = 7;
	public static final int C_STRING_INFO = 8;
	public static final int C_FIELDREF_INFO = 9;
	public static final int C_METHODREF_INFO = 10;
	public static final int C_NAME_AND_TYPE_INFO = 12;
	private int size;
	private List<ConstantInfo> constantInfosList = new ArrayList<>();
	
	public void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return this.size;
	}

	public ConstantInfo getConstantInfo(int i) {
		return this.constantInfosList.get(i);
	}

	public void addConstantInfo(ConstantInfo constantInfo) {
		this.constantInfosList.add(constantInfo);
	}
	public String getUTF8String(int index) {
		return ((UTF8Info)getConstantInfo(index)).getValue();
	}
}
