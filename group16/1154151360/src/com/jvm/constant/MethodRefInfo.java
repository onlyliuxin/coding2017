package com.jvm.constant;

public class MethodRefInfo extends ConstantInfo{

	private int tag = ConstantInfo.METHODREF_INFO;
	
	private int class_index;
	
	private int nameAndType_index;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getClass_index() {
		return class_index;
	}

	public void setClass_index(int class_index) {
		this.class_index = class_index;
	}

	public int getNameAndType_index() {
		return nameAndType_index;
	}

	public void setNameAndType_index(int nameAndType_index) {
		this.nameAndType_index = nameAndType_index;
	}

	@Override
	public String toString() {
		return "MethodrefInfo [tag=" + tag + ", class_index=" + class_index
				+ ", nameAndType_index=" + nameAndType_index + "]";
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visitMethodRef(this);;
		
	}

	@Override
	public int getType() {
		
		return getTag();
	}

	

	

	
}
