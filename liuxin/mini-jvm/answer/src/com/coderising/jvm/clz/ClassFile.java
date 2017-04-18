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
	
	private AccessFlag accessFlag;
	private ClassIndex clzIndex;
	private ConstantPool pool;
	private List<Field> fields = new ArrayList<Field>();
	private List<Method> methods = new ArrayList<Method>();
	
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
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:"+ getClassName());
		
		System.out.println("Super Class Name:"+ getSuperClassName());
		
		
	}
	
	public String getClassName(){
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo)this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}
	public String getSuperClassName(){
		ClassInfo superClass = (ClassInfo)this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
	
	public Method getMethod(String methodName, String paramAndReturnType){
		
		for(Method m :methods){
			
			int nameIndex = m.getNameIndex();
			int descriptionIndex = m.getDescriptorIndex();
			
			String name = this.getConstantPool().getUTF8String(nameIndex);
			String desc = this.getConstantPool().getUTF8String(descriptionIndex);
			if(name.equals(methodName) && desc.equals(paramAndReturnType)){
				return m;
			}
		}
		return null;
	}
	public Method getMainMethod(){
		for(Method m :methods){
			int nameIndex = m.getNameIndex();
			int descIndex = m.getDescriptorIndex();
			String name = this.getConstantPool().getUTF8String(nameIndex);
			String desc = this.getConstantPool().getUTF8String(descIndex);
			if(name.equals("main")  && desc.equals("([Ljava/lang/String;)V")){
				return m;
			}
		}
		return null;
	}
}
