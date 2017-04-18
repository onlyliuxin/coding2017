package com.coderising.jvm.constant;

public class MethodInfo extends ConstantInfo {

	private int tag = ConstantInfo.METHOD_INFO;
	private int Index_ClassInfo;
	private int Index_NameAndType;

	public MethodInfo(ConstantPool constantPool) {
		super(constantPool);
	}

	public int getIndex_ClassInfo() {
		return Index_ClassInfo;
	}

	public void setIndex_ClassInfo(int index_ClassInfo) {
		Index_ClassInfo = index_ClassInfo;
	}

	public int getIndex_NameAndType() {
		return Index_NameAndType;
	}

	public void setIndex_NameAndType(int index_NameAndType) {
		Index_NameAndType = index_NameAndType;
	}

	public String getClassName() {

		ConstantPool pool = this.getConstantPool();

		ClassInfo classInfo = (ClassInfo) pool
				.getConstantInfo(getIndex_ClassInfo());
		return classInfo.getClassName();
	}

	public String getParameterAndTypeString() {
		int index = getIndex_NameAndType();
		ConstantPool pool = this.getConstantPool();

		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool
				.getConstantInfo(index);
		return nameAndTypeInfo.getDescribeInfo();

	}

	public String getMethodName() {

		int index = getIndex_NameAndType();
		ConstantPool pool = this.getConstantPool();

		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) pool
				.getConstantInfo(index);
		return nameAndTypeInfo.getNameInfo();
	}

	@Override
	public int getType() {
		return this.tag;
	}

}
