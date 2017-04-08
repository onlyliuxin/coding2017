package com.coderising.jvm.constant;

public class FieldRefInfo extends ConstantInfo {
	private int classInfoIndex;
	private int nameAndTypeIndex;
	public FieldRefInfo(int classInfoIndex, int nameAndTypeIndex) {
		super();
		this.classInfoIndex = classInfoIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	public int getClassInfoIndex() {
		return classInfoIndex;
	}
	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
	
}
