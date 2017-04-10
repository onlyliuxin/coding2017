package com.coderising.jvm.constant;

public class StringInfo extends ConstantInfo {
	private int type = STRING_INFO;//u1 tag
	private int string_index;//u2 string_index
	@Override
	public int getType() {
		return type;
	}
	public int getString_index() {
		return string_index;
	}
	public void setString_index(int string_index) {
		this.string_index = string_index;
	}
	public String getString(){
		UTF8Info u = (UTF8Info)this.constantPool.getConstantInfo(string_index);
		return u.getValue();
	}
	@Override
	public String toString() {
		return getString();
	}

}
