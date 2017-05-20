package com.jvm.clz;

import java.util.List;

import com.jvm.constant.ClassInfo;
import com.jvm.constant.ConstantPool;


public class ClassFile {

	private int minorVersion;
	
	private int majorVersion;
	
	private ConstantPool constantPool;
	
	private AccessFlag accessFlag;
	
	private ClassIndex classIndex;

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

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}

	public ClassIndex getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(ClassIndex classIndex) {
		this.classIndex = classIndex;
	}
	
	public void print(){
		if (this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public");
		}
		
		System.out.println("Class Name: " );
	}
	
	public String getClassName(){
		int thisClassIndex = this.classIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
		return this.getConstantPool().getUTF8String(thisClass.getUTF8_index());
	}
	
	public String getSuperClassName(){
		ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.classIndex.getSuperClassIndex());
		return this.getConstantPool().getUTF8String(superClass.getUTF8_index());
	}
	
}
