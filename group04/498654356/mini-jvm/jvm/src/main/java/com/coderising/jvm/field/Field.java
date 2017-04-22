package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;

public class Field {

	private int accessFlag;
	private int nameIndex;
	private int descIndex;
	private ConstantPool constantPool;
	public ConstantPool getConstantPool() {
		return constantPool;
	}
	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}
	public int getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}
	public int getNameIndex() {
		return nameIndex;
	}
	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}
	public int getDescIndex() {
		return descIndex;
	}
	public void setDescIndex(int descIndex) {
		this.descIndex = descIndex;
	}
	
	@Override
	public String toString() {
		String name = ((UTF8Info) this.constantPool.getConstantInfo(nameIndex)).getValue();
		String desc = ((UTF8Info) this.constantPool.getConstantInfo(descIndex)).getValue();
		return name + ":" + desc;
	}
	
}
