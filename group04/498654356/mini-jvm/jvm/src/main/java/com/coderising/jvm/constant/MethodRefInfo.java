package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo {
	private int classInfoIndex;
	private int nameAndTypeIndex;

	public MethodRefInfo(int classInfoIndex, int nameAndTypeIndex) {
		super();
		this.classInfoIndex = classInfoIndex;
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	public int getClassInfoIndex() {
		return this.classInfoIndex;
	}

	public int getNameAndTypeIndex() {
		return this.nameAndTypeIndex;
	}

}
