package com.coderising.jvm.clasfile;

public class AccessFlag {

	private int flag;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public boolean isPublic(){
		return (this.flag & 0x0001) != 0;
	}
	
	public boolean isFinalClass(){
		return (this.flag & 0x0010) != 0;
	}
}
