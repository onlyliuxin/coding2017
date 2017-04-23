package com.coderising.jvm.clz;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;

public class ClassFile {
	private String magicNumer;
	
	private int minorVersion;
	private int majorVersion;
	
	private AccessFlag accessFlag;
	private ClassIndex clzIndex;
	private ConstantPool pool;
	
	
	
	public String getMagicNumer() {
		return magicNumer;
	}
	public void setMagicNumer(String magicNumer) {
		this.magicNumer = magicNumer;
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
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}
	public ClassIndex getClzIndex() {
		return clzIndex;
	}
	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;		
	}
	
	public ConstantPool getPool() {
		return pool;
	}
	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}
	
	public void print(){
		
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:"+ getClassName());
		
		System.out.println("Super Class Name:"+ getSuperClassName());
		
		
	}
	
	private String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	private String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}


}
