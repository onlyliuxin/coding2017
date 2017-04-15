package com.coderising.jvm.clz;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;

public class ClassFile {
	private int minorVersion;
	private int majorVersion;
	
	private AccessFlag accessFlag;// �ڳ�����֮��
	private ClassIndex clzIndex;// �ڷ��ʱ�־֮��, �����������������������ͽӿ�����  
	private ConstantPool pool; 


	public AccessFlag getAccessFlag(){
		return accessFlag;
	}
	
	public ClassIndex getClzIndex(){
		return clzIndex;
	}
	
	public void setClassIndex(ClassIndex clzIndex){
		this.clzIndex = clzIndex;
	}
	
	public int getMinorVersion(){
		System.out.println(minorVersion);
		return minorVersion;
	}
	
	public void setMinorVersion(int minorVersion){
		this.minorVersion = minorVersion;
	}
	
	public int getMajorVersion(){
		return majorVersion;
	}
	
	public void setMajorVersion(int majorVersion){
		this.majorVersion = majorVersion;
	}
	
	public ConstantPool getConstantPool(){
		return this.pool;
	}
	public void setConstantPool(ConstantPool pool){
		this.pool = pool;
	}
	
	public void print(){
		if(this.accessFlag.isPublicClass()){
			System.out.println("Access flag : public ");
		}
		System.out.println("ClassName: " + getClassName());
		System.out.println("SuperClassName: " + getSuperClassName());
		
	}
	
	public void setAccessFlag(AccessFlag accessFlag){
		this.accessFlag = accessFlag;
	}
	
	private String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	
	private String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
	
}