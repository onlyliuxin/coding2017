package com.coderising.jvm.constant;

public class FieldRefInfo extends ConstantInfo {
	private int type = FIELD_INFO;// u1 tag
	private int class_index;// u2 class_index
	private int name_and_type_index;// u2 name_and_type_index

	public FieldRefInfo(ConstantPool pool) {
		super(pool);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void print() {
		System.out.println("u1 tag:" + getType() + " FieldInfo"
				+ ",u2 class_index:" + getClass_index()
				+ ",u2 name_and_type_index:" + getName_and_type_index());
	}

	@Override
	public String toString() {
		return getClassName() + ":" + getFieldName() + " : " + getFieldType();
	}

	public String getClassName() {
		ClassInfo classInfo = (ClassInfo) this.constantPool
				.getConstantInfo(class_index);
		return classInfo.getClassName();
	}

	public String getFieldName() {
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(name_and_type_index);
		return nameAndTypeInfo.getName();
	}

	public String getFieldType() {
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) this.constantPool
				.getConstantInfo(name_and_type_index);
		return nameAndTypeInfo.getTypeInfo();
	}

	/*
	 * getter setter
	 */
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

}
