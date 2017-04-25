package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class FieldRefInfo extends ConstantInfo{

	public FieldRefInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	public FieldRefInfo() {
		// TODO Auto-generated constructor stub
	}
	private int tag = ConstantInfo.FIELDREF_INFO;
	private int clz_index;
	private int nameAndType_index;
	public int getClz_index() {
		return clz_index;
	}
	public void setClz_index(int clz_index) {
		this.clz_index = clz_index;
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
		visitor.visitFieldRef(this);
	}
	
}
