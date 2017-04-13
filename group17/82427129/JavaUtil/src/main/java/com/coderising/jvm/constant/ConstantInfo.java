package com.coderising.jvm.constant;

public abstract class ConstantInfo {
	public static final int UTF8_INFO = 1;
	public static final int INTEGER_INFO = 3;
	public static final int FLOAT_INFO = 4;
	public static final int LONG_INFO = 5;
	public static final int DOUBLE_INFO = 6;
	public static final int CLASS_INFO = 7;
	public static final int STRING_INFO = 8;
	public static final int FIELD_INFO = 9;
	public static final int METHOD_INFO = 10;
	public static final int NAME_AND_TYPE_INFO = 12;
	
	protected ConstantPool constantPool;
	
	public ConstantInfo() {}
	
	public ConstantInfo(ConstantPool cp){
		this.constantPool = cp;
	}
	
	public abstract int getType();
	
	public abstract void print();
	
	/*
	 * getter setter
	 */
	public ConstantPool getConstantPool(){
		return this.constantPool;
	}
	
	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);
	}
}
