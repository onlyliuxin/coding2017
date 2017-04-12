package com.coding.basic.homework_04.jvm.clz;

import com.coding.basic.homework_04.jvm.attr.AccessFlag;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class ClassFile {

	private String MagicNumber;
	
	private int minorVersion;
	private int majorVersion;
	private ConstantPool constantPool;
	private int ConstantNum;
	private ClassIndex clzIndex;
	
	private AccessFlag accessFlag;
	
	
	
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}



	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}



	public ClassIndex getClzIndex() {
		return clzIndex;
	}



	public void setClzIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;
	}



	public int getConstantNum() {
		return ConstantNum;
	}



	public void setConstantNum(int constantNum) {
		ConstantNum = constantNum;
	}



	public void print() {
		System.out.println("MagicNumber: "+ MagicNumber);
		System.out.println( "minorVersion" +minorVersion);
		System.out.println("majorVersion" + majorVersion);
		
	}
	
	

	public String getMagicNumber() {
		return MagicNumber;
	}



	public void setMagicNumber(String magicNumber) {
		MagicNumber = magicNumber;
	}



	public int getMinorVersion() {
		return minorVersion;
	}


	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}


	public int getMajorVersion() {
		return majorVersion;
	}


	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}


	public ConstantPool getConstantPool() {
		return constantPool;
	}


	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	
}
