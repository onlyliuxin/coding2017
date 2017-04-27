package com.donaldy.jvm.constant;

public class StringInfo extends ConstantInfo{
	private int type = ConstantInfo.STRING_INFO;
	private int index;
	public StringInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	
	public String toString(){
		return this.getConstantPool().getUTF8String(index);
	}
	
}
