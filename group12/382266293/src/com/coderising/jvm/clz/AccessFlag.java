package com.coderising.jvm.clz;

import com.coderising.jvm.loader.ByteCodeIterator;

public class AccessFlag {
	public static AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		AccessFlag accessFlag = new AccessFlag(iter.nextU2ToInt());
		return accessFlag;
	}

	private int flagValue;

	public AccessFlag(int value) {
		this.flagValue = value;
	}

	public int getFlagValue() {
		return flagValue;
	}

	public boolean isFinalClass() {
		return (this.flagValue & 0x0010) != 0;
	}

	public boolean isPublicClass() {
		return (this.flagValue & 0x0001) != 0;
	}

	public void setFlagValue(int flag) {
		this.flagValue = flag;
	}
}