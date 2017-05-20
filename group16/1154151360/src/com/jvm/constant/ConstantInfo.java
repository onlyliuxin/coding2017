package com.jvm.constant;

public abstract class ConstantInfo {
	public final static int UTF8_INFO = 1;
	public final static int FLOAT_INFO = 4;
	public final static int CLASS_INFo = 7;
	public final static int STRING_INFO = 8;
	public final static int FIELDREF_INFO = 9;
	public final static int METHODREF_INFO = 10;
	public final static int NAME_AND_TYPE_INFO = 14;
	
	ConstantPool constantPool;

	public ConstantInfo() {
	
	}
	
	public abstract int getType();

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public ConstantInfo getConstantInfo(int index){
		return this.constantPool.getConstantInfo(index);
	}
	
	public abstract void accept(Vistor vistor);
	
	public static interface Vistor{
		public void visitClassInfo(ClassInfo info);
		public void visitFieldRef(FieldRefInfo info);
		public void visitMethodRef(MethodRefInfo info);
		public void visitNameAndType(NameAndTypeInfo info);
		public void visitString(StringInfo info);
		public void visistUTF8(UTF8Info info);
	}
	
}
