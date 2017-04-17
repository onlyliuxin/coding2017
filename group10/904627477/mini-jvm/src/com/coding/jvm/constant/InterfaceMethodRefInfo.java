package com.coding.jvm.constant;

public class InterfaceMethodRefInfo extends ConstantInfo {
	
	private int type = ConstantInfo.INTERFACEMETHODREF_INFO;
	private int classInfoIndex;
	private int nameAndTypeIndex;

	public InterfaceMethodRefInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
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




	public int getType() {
		return type;
	}

}
