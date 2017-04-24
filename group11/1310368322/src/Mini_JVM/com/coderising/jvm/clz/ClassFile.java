package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFile {
	private int minorVersion;
	private int majorVersion;
	
	private AccessFlag accessFlag;// 在常量池之后
	private ClassIndex clzIndex;// 在访问标志之后, 包括：类索引、父类索引和接口索引  
	private ConstantPool pool; 
	private List<Field> fields = new ArrayList<Field>();
	private List<Method> methods = new ArrayList<Method>();


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
	
	public void addField(Field f){
		this.fields.add(f);
	}
	public List<Field> getFields(){
		return this.fields;
	}
	public void addMethod(Method m){
		this.methods.add(m);
	}
	public List<Method> getMethods() {
		return methods;
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
