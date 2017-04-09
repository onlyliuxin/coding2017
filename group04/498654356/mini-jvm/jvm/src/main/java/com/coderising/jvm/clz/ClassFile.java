package com.coderising.jvm.clz;

import com.coderising.jvm.constant.ConstantPool;

public class ClassFile {

	private int minorVersion;
	private int MajorVersion;
	private ConstantPool constantPool;
	private ClassIndex classIndex;
	private AccessFlag accessFlag;
	
	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public int getMajorVersion() {
		return MajorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		MajorVersion = majorVersion;
	}
	
	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}

	public void print() {
		// TODO Auto-generated method stub
		throw new RuntimeException("no implement");
	}

	public ClassIndex getClzIndex() {
		return classIndex;
	}

	public void setClassIndex(ClassIndex classIndex) {
		this.classIndex = classIndex;
	}

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}
	
}
