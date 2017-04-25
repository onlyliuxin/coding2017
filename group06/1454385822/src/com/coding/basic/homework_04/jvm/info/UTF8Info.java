package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class UTF8Info extends ConstantInfo{

	private int tag = ConstantInfo.UTF8_INFO;
	private int length;
	private String value;
	
	public UTF8Info(ConstantPool pool) {
		super(pool);
	}
	public UTF8Info() {
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "UTF8Info [tag=" + tag + ", length=" + length + ", value=" + value +")]";
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public int getType() {
		return tag;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visistUTF8(this);
	}
	
	
}
