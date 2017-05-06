package com.coderising.jvm.constant;

import com.coderising.jvm.print.PrintVisitor;

public class StringInfo extends ConstantInfo {
	private int type = ConstantInfo.STRING_INFO;
	private int index;

	public StringInfo(ConstantPool pool) {
		super(pool);
	}

	public int getIndex() {
		return index;
	}

	@Override
	public int getType() {
		return type;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return this.getConstantPool().getUTF8String(index);
	}
	
	@Override
	public void accept(PrintVisitor visitor) {
		visitor.visit(this);
		
	}

}
