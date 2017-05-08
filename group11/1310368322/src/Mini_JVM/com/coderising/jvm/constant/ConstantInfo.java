package com.coderising.jvm.constant;

public abstract class ConstantInfo {
	public static final int UTF8_INFO = 1;
	public static final int FLOAT_INFO = 4;
	public static final int CLASS_INFO = 7;
	public static final int STRING_INFO = 8;
	public static final int FIELD_INFO = 9;
	public static final int METHOD_INFO = 10;
	public static final int NAME_AND_TYPE_INFO = 12;
	
	protected ConstantPool constantPool;
	
	public ConstantInfo(){
		
	}
	
	public ConstantInfo(ConstantPool pool){//  因为各个常量项互相之间要引用
		this.constantPool = pool;
	}
	
	public abstract int getType();
	
	public ConstantPool getConstantPool(){
		return constantPool;
	}
	
	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);// 这里调用的是 ConstantInfo 类中 的变量 constantPool 中的 getConstantInfo方法，
											// ContantInfo 类中的 getConstantInfo 方法名相同
	}
	
	
}
