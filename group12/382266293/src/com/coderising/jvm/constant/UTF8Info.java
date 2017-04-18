package com.coderising.jvm.constant;

import com.coderising.jvm.print.PrintVisitor;

public class UTF8Info extends ConstantInfo {
	private int type = ConstantInfo.UTF8_INFO;
	private int length;
	private String value;

	public UTF8Info(ConstantPool pool) {
		super(pool);
	}

	public int getLength() {
		return length;
	}

	@Override
	public int getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "UTF8Info [type=" + type + ", length=" + length + ", value=" + value + ")]";
	}

	@Override
	public void accept(PrintVisitor visitor) {
		visitor.visit(this);
		
	}


}
