package com.coderising.jvm.constant;

public class ClassInfo extends ConstantInfo {
	private int type = CLASS_INFO;// u1 tag
	private int name_index;// u2 name_index

	@Override
	public int getType() {
		return type;
	}

	public int getName_index() {
		return name_index;
	}

	public void setName_index(int name_index) {
		this.name_index = name_index;
	}

	public String getClassName() {
		int name_index = getName_index();
		UTF8Info utf8 = (UTF8Info) this.constantPool
				.getConstantInfo(name_index);
		return utf8.getValue();
	}
}
