package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class MethodRefInfo extends ConstantInfo {

	public MethodRefInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	public MethodRefInfo() {
		// TODO Auto-generated constructor stub
	}
	private int tag = ConstantInfo.METHODREF_INFO;
	private int class_index;
	private int nameAndType_index;
	
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
	public int getTag() {
		return tag;
	}
	@Override
	public int getType() {
		return tag;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visitMethodRef(this);
	}
	
	
	
}
