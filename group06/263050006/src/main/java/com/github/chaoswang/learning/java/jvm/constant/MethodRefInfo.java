package com.github.chaoswang.learning.java.jvm.constant;
/**
 * CONSTANT_Methodref_info { 
 * u1 tag; 
 * u2 class_index; 
 * u2 name_and_type_index;
 */
public class MethodRefInfo extends ConstantInfo {

	
	private int type = ConstantInfo.METHOD_INFO;
	private int classInfoIndex;
	private int nameAndTypeIndex;

	public MethodRefInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
	}

	public int getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(int classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(int nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	public String toString() {

		return getClassName() + " : " + this.getMethodName() + " : " + this.getParamAndReturnType();
	}

	public String getClassName() {
		ConstantPool pool = this.getConstantPool();
		ClassInfo clzInfo = (ClassInfo) pool.getConstantInfo(this.getClassInfoIndex());
		return clzInfo.getClassName();
	}

	public String getMethodName() {
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getName();
	}

	public String getParamAndReturnType() {
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo typeInfo = (NameAndTypeInfo) pool.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getTypeInfo();
	}

}
