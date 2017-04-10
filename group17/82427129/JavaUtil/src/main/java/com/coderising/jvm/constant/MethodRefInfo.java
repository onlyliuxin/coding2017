package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo {
	private int type = METHOD_INFO;//u1 tag
	private int class_index;//u2 class_index
	private int name_and_type_index;//u2 name_and_type_index

	@Override
	public int getType() {
		return type;
	}

	public int getClass_index() {
		return class_index;
	}

	public void setClass_index(int class_index) {
		this.class_index = class_index;
	}

	public int getName_and_type_index() {
		return name_and_type_index;
	}

	public void setName_and_type_index(int name_and_type_index) {
		this.name_and_type_index = name_and_type_index;
	}

	public String getClassName() {
		ClassInfo c = (ClassInfo) this.constantPool
				.getConstantInfo(class_index);
		return c.getClassName();
	}

	public String getMethodName() {
		NameAndTypeInfo nt = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(name_and_type_index);
		return nt.getName();
	}

	public String getParamsAndReturnType() {
		NameAndTypeInfo nt = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(name_and_type_index);
		return nt.getTypeInfo();
	}

	@Override
	public String toString() {
		return getClassName() + ":" + getMethodName() + ":"
				+ getParamsAndReturnType();
	}
}
