package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class StringInfo extends ConstantInfo {

	public StringInfo(ConstantPool pool) {
		super(pool);
	}
	public StringInfo() {
		// TODO Auto-generated constructor stub
	}
	private int tag = ConstantInfo.STRING_INFO;
	private int string_index;
	public int getString_index() {
		return string_index;
	}
	public void setString_index(int string_index) {
		this.string_index = string_index;
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
		visitor.visitString(this);
	}
	
	
}
