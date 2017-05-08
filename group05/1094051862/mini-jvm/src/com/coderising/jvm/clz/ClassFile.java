package com.coderising.jvm.clz;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;
import com.coding.basic.ArrayList;
import com.coding.basic.List;
public class ClassFile {
	
	private int minorVersion;
	private int majorVersion;
	
	private AccessFlag accessFlag;
	private ClassIndex clzIndex;
	private ConstantPool pool;
	
	private List fields = new ArrayList();
	private List methods = new ArrayList();
	public void addField(Field f) {
		fields.add(f);
	}
	
	public List getFields() {
		return fields;
	}

	public List getMethods() {
		return methods;
	}
	public ClassIndex getClzIndex() {
		return clzIndex;
	}
	public AccessFlag getAccessFlag() {
		return accessFlag;
	}
	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}
	
	
	
	public ConstantPool getConstantPool() {		
		return pool;
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
	public void setConstPool(ConstantPool pool) {
		this.pool = pool;
		
	}
	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;		
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
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	private String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}

	public void addMethod(Method m) {
		methods.add(m);
	}
}
