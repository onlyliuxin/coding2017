package com.coderising.jvm.constant;

public class ClassInfo extends ConstantInfo{
	
	private int nameIndex;
	private ConstantPool constantPool;

	public ClassInfo(int nameIndex, ConstantPool constantPool) {
		super();
		this.nameIndex = nameIndex;
		this.constantPool = constantPool;
	}

	public int getUtf8Index() {
		return this.nameIndex;
	}

	public String getClassName() {
		return ((UTF8Info)this.constantPool.getConstantInfo(this.nameIndex)).getValue();
	}

}
