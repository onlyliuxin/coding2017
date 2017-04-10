package com.coderising.jvm.method;

public class Method {
	private int access_flags;
	private int name_index;
	private int type_index;
	
	
	public int getAccess_flags() {
		return access_flags;
	}
	public void setAccess_flags(int access_flags) {
		this.access_flags = access_flags;
	}
	public int getName_index() {
		return name_index;
	}
	public void setName_index(int name_index) {
		this.name_index = name_index;
	}
	public int getType_index() {
		return type_index;
	}
	public void setType_index(int type_index) {
		this.type_index = type_index;
	}
}
