package com.github.miniyk2012.coding2017.coderising.jvm.constant;

import com.github.miniyk2012.coding2017.coderising.jvm.print.ConstantPoolPrinter;

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

	@Override
	public void accept(ConstantPoolPrinter.Visitor visitor) {
		visitor.visitStringInfo(this);
	}
}
