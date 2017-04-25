package com.coding.basic.homework_04.jvm.constant;

import com.coding.basic.homework_04.jvm.info.ClassInfo;
import com.coding.basic.homework_04.jvm.info.FieldRefInfo;
import com.coding.basic.homework_04.jvm.info.MethodRefInfo;
import com.coding.basic.homework_04.jvm.info.NameAndTypeInfo;
import com.coding.basic.homework_04.jvm.info.StringInfo;
import com.coding.basic.homework_04.jvm.info.UTF8Info;

public abstract class ConstantInfo {

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

	public abstract int getType();
	
	public abstract void accept(Visitor visitor);
	
	public static interface Visitor{
		public void visitClassInfo(ClassInfo info);
		public void visitFieldRef(FieldRefInfo info);
		public void visitMethodRef(MethodRefInfo info);
		public void visitNameAndType(NameAndTypeInfo info);
		public void visitString(StringInfo info);
		public void visistUTF8(UTF8Info info);
		
	}

}
