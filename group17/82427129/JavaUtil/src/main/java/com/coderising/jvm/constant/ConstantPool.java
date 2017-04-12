package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	private List<ConstantInfo> cl = new ArrayList<ConstantInfo>();
	private int constantPoolSize;

	public void addConstantInfo(ConstantInfo e){
		cl.add(e);
	}

	public ConstantInfo getConstantInfo(int index) {
		return cl.get(index);
	}

	public int getSize() {
		return cl.size() - 1;// 减去常量池的长度一项
	}
	/*
	 * getter setter
	 */
	public int getConstantPoolSize() {
		return constantPoolSize;
	}
	
	public void setConstantPoolSize(int constantPoolSize) {
		this.constantPoolSize = constantPoolSize;
	}

	public String getUTF8String(int nameIndex) {
		return ((UTF8Info)getConstantInfo(nameIndex)).getValue();
	}
}
