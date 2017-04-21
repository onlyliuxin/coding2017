package com.coderising.jvm.clasfile;

import com.coderising.jvm.constant.ConstantPool;

public class ClassFile {

	private int MinorVersion;
	private int MajorVersion;
	private String MagicNumer;
	private ConstantPool pool;
	private ClassIndex classIndex;
	private AccessFlag accessFlag;

	public ConstantPool getPool() {
		return pool;
	}

	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}

	public String getMagicNumer() {
		return MagicNumer;
	}

	public void setMagicNumer(String magicNumer) {
		this.MagicNumer = magicNumer;
	}

	public int getMinorVersion() {
		return MinorVersion;
	}

	public int getMajorVersion() {
		return MajorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.MinorVersion = minorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.MajorVersion = majorVersion;
	}

	public ConstantPool getConstantPool() {
		
		return this.pool;
	}

	public ClassIndex getClassIndex() {
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
