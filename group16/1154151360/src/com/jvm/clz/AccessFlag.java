package com.jvm.clz;

public class AccessFlag {
	private int flagValue;

	private AccessFlag(int flagValue) {
		this.flagValue = flagValue;
	}

	public int getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(int flagValue) {
		this.flagValue = flagValue;
	}
	
	public boolean isPublicClass(){
		return (this.flagValue & 0x0001) != 0;
	}
	
	public boolean isFinalClass(){
		
		return (this.flagValue & 0x0010) != 0;
	}
}
