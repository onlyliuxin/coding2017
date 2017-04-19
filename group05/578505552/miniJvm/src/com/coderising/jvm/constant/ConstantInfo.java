package com.coderising.jvm.constant;

public abstract class ConstantInfo {

	public static final Integer UTF8_INFO = 1;
	public static final Integer INTEGER_INFO = 3;
	public static final Integer FLOAT_INFO = 4;
	public static final Integer LONG_INFO = 5;
	public static final Integer DOUBLE_INFO = 6;
	public static final Integer CLASS_INFO = 7;
	public static final Integer STRING_INFO = 8;
	public static final Integer FIELDREF_INFO = 9;
	public static final Integer METHODREF_INFO = 10;
	public static final Integer INTERFACE_METHODREF_INFO = 11;
	public static final Integer NAMEANDTYPE_INFO = 12;
	public static final Integer METHODHANDLE_INFO = 15;
	public static final Integer METHODTYPE_INFO = 16;
	public static final Integer INVOKEDYNAMIC_INFO = 18;

	protected ConstantPool constantPool;
	
	public ConstantInfo(){
		
	}
	
	public ConstantInfo(ConstantPool pool) {
		this.constantPool = pool;
	}

	public abstract int getType();
	
	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);
	}
	
}
