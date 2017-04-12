package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo {
	private int type = METHOD_INFO;//u1 tag
	private int classInfoIndex;//u2 class_index
	private int nameAndTypeIndex;//u2 name_and_type_index

	@Override
	public int getType() {
		return type;
	}

	public int getClassInfoIndex() {
		return classInfoIndex;
	}

	public void setClassInfoIndex(int class_index) {
		this.classInfoIndex = class_index;
	}

	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(int name_and_type_index) {
		this.nameAndTypeIndex = name_and_type_index;
	}

	public String getClassName() {
		ClassInfo c = (ClassInfo) this.constantPool
				.getConstantInfo(classInfoIndex);
		return c.getClassName();
	}

	public String getMethodName() {
		NameAndTypeInfo nt = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(nameAndTypeIndex);
		return nt.getName();
	}

	public String getParamsAndReturnType() {
		NameAndTypeInfo nt = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(nameAndTypeIndex);
		return nt.getTypeInfo();
	}

	@Override
	public String toString() {
		return getClassName() + ":" + getMethodName() + ":"
				+ getParamsAndReturnType();
	}
}
