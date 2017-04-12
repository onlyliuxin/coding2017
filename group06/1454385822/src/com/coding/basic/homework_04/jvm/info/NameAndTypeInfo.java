package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class NameAndTypeInfo extends ConstantInfo {

	private int tag = ConstantInfo.NAMEANDTYPE_INFO;
	private int clz_index;
	private int descriptor_index;
	
	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	public NameAndTypeInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getClz_index() {
		return clz_index;
	}
	public void setClz_index(int clz_index) {
		this.clz_index = clz_index;
	}
	
	public int getDescriptor_index() {
		return descriptor_index;
	}
	public void setDescriptor_index(int descriptor_index) {
		this.descriptor_index = descriptor_index;
	}
	public int getTag() {
		return tag;
	}
	
	
}
