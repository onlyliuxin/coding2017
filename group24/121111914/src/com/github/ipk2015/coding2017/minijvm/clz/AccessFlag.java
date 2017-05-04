package com.github.ipk2015.coding2017.minijvm.clz;



public class AccessFlag {
	public static int ACC_PUBLIC = 0x0001;
	public static int ACC_FINAL = 0x0002;
	public static int ACC_SUPER = 0x0020;
	public static int ACC_INTEERFACE = 0x0200;
	public static int ACC_ABSTRACT = 0x0400;
	public static int ACC_SYNTHETIC = 0x1000;
	public static int ACC_ANNOTATION = 0x2000;
	public static int ACC_ENUM = 0x4000;
	
	private int flagValue;

	public AccessFlag(int value) {
		this.flagValue = value;
	}

	public int getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(int flag) {
		this.flagValue = flag;
	}
	
	public boolean isPublicClass(){
		return (this.flagValue & ACC_PUBLIC) != 0;
	}
	
	public boolean isFinalClass(){
		return (this.flagValue & ACC_FINAL) != 0;
	}
	
}