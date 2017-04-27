package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo {
	private int type = METHOD_INFO;// u1 tag
	private int classInfoIndex;// u2 class_index
	private int nameAndTypeIndex;// u2 name_and_type_index

	public MethodRefInfo(ConstantPool pool) {
		super(pool);
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
	public int getType() {
		return type;
	}

	@Override
	public void print() {
		System.out.println("u1 tag:" + getType() + " MethodInfo"
				+ ",u2 class_index:" + getClassInfoIndex()
				+ ",u2 name_and_type_index" + getNameAndTypeIndex());
	}

	@Override
	public String toString() {
		return getClassName() + ":" + getMethodName() + ":"
				+ getParamsAndReturnType();
	}

	/*
	 * getter setter
	 */
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

}
