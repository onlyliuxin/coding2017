package com.coderising.jvm.constant;

import com.coderising.jvm.print.PrintVisitor;

public class NameAndTypeInfo extends ConstantInfo {
	public int type = ConstantInfo.NAME_AND_TYPE_INFO;

	private int index1;
	private int index2;

	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
	}

	public int getIndex1() {
		return index1;
	}

	public int getIndex2() {
		return index2;
	}

	public String getName() {
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info1 = (UTF8Info) pool.getConstantInfo(index1);
		return utf8Info1.getValue();
	}

	@Override
	public int getType() {
		return type;
	}

	public String getTypeInfo() {
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info2 = (UTF8Info) pool.getConstantInfo(index2);
		return utf8Info2.getValue();
	}

	public void setIndex1(int index1) {
		this.index1 = index1;
	}

	public void setIndex2(int index2) {
		this.index2 = index2;
	}

	@Override
	public String toString() {
		return "(" + getName() + "," + getTypeInfo() + ")";
	}
	
	@Override
	public void accept(PrintVisitor visitor) {
		visitor.visit(this);		
	}
}
