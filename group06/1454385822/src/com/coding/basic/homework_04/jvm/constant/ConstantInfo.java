package com.coding.basic.homework_04.jvm.constant;

import org.omg.CORBA.PRIVATE_MEMBER;

public class ConstantInfo {

	public static final int CLASS_INFO = 7;
	public static final int UTF8_INFO = 1;
	public static final int STRING_INFO = 8;
	public static final int FIELDREF_INFO = 9;
	public static final int METHODREF_INFO = 10;
	public static final int NAMEANDTYPE_INFO = 12;
	
	protected ConstantPool pool;
	public ConstantInfo(){
			
	}
	public ConstantInfo(ConstantPool pool) {
		this.pool = pool;
	}


}
