package com.coderising.jvm.constant;

public class ClassInfo extends ConstantInfo {
	private int type = CLASS_INFO;// u1 tag
	private int utf8Index;// u2 name_index

	public ClassInfo(ConstantPool c) {
		super(c);
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void print() {
		System.out.println("u1 tag:" + getType() + " ClassInfo"
				+ ",u2 name_index:" + getUtf8Index());
	}

	public String getClassName() {
		int name_index = getUtf8Index();
		UTF8Info utf8 = (UTF8Info) this.constantPool
				.getConstantInfo(name_index);
		return utf8.getValue();
	}

	/*
	 * getter setter
	 */
	public int getUtf8Index() {
		return utf8Index;
	}

	public void setUtf8Index(int name_index) {
		this.utf8Index = name_index;
	}
}
