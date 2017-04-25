package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class NameAndTypeInfo extends ConstantInfo {

	private int tag = ConstantInfo.NAMEANDTYPE_INFO;
	private int index1;
	private int index2;
	
	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	public NameAndTypeInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getIndex1() {
		return index1;
	}
	public void setIndex1(int index1) {
		this.index1 = index1;
	}
	
	public int getIndex2() {
		return index2;
	}
	public void setIndex2(int index2) {
		this.index2 = index2;
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
		visitor.visitNameAndType(this);
	}
	
	
}
