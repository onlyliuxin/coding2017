package com.sprint.jvm.clz;

import com.sprint.jvm.constant.ConstantPool;
import com.sprint.jvm.constant.ClassInfo;
public class ClassFile {
	private int minorVersion;
	private int majorVersion;

	private AccessFlag accessFlag;
	private ClassIndex clzIndex;
	private ConstantPool pool;

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

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	} 

	public ClassIndex getClassIndex() {
		return clzIndex;
	}

	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;
	}

	public ConstantPool getConstantPool() {
		return pool;
	}

	public void setConstantPool(ConstantPool pool) {
		this.pool = pool;
	}

	public void print() {
		if (this.accessFlag.isPublicClass()) {
			System.out.println("Access flag : public ");
		}
		System.out.println("Class Name:" + getClassName() );
		System.out.println("Super Class Name:" + getSuperClassName());
	}

	private String getClassName() {
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);	
		return thisClass.getClassName();
	}

	private String getSuperClassName() {
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
}
