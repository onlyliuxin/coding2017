package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.UTF8Info;
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

	public void addField(Field field) {
		
		this.fields.add(field);
		
	}

	public void addMethod(Method method) {
		
		this.methods.add(method);
		
	}

	public AccessFlag getAccessFlag() {
		return accessFlag;
	}

	public String getClassName() {
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}

	public ClassIndex getClzIndex() {
		return clzIndex;
	}

	public ConstantPool getConstantPool() {
		return pool;
	}

	public List<Field> getFields() {

		return fields;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public List<Method> getMethods() {

		return methods;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public String getSuperClassName() {
		ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}

	public void print() {

		if (this.accessFlag.isPublicClass()) {
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:" + getClassName());

		System.out.println("Super Class Name:" + getSuperClassName());

	}

	public void setAccessFlag(AccessFlag accessFlag) {
		this.accessFlag = accessFlag;
	}

	public void setClassIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;
	}
	
	public void setConstPool(ConstantPool pool) {
		this.pool = pool;

	}
	
	public void setFields(List<Field> fields) {
		
		this.fields = fields;
		
	}

	public void setMajorVersion(int majorVersion) {
		this.majorVersion = majorVersion;
	}
	
	public void setMethods(List<Method> methods) {
		
		this.methods = methods;
		
	}
	
	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	
	public Method getMethod(String methodName, String paramAndReturnType){

		
		for (Method m : methods) {
			
			int nameIndex = m.getNameIndex();
			int descriptorIndex= m.getDescriptorIndex();
			String name = ((UTF8Info)pool.getConstantInfo(nameIndex)).getValue();
			String desc = ((UTF8Info)pool.getConstantInfo(descriptorIndex)).getValue();
			if (name.equals(methodName) && desc.equals(paramAndReturnType)) {
				return m;
			}		
		}
		
		return null;
	}
	
	public Method getMainMethod(){
		
		return getMethod("main", "([Ljava/lang/String;)V");

	}
	
	public Method getMethod(MethodRefInfo methodRef){

		String methodName = methodRef.getMethodName();
		String paramAndReturnType = methodRef.getParamAndReturnType();
	
		return getMethod(methodName, paramAndReturnType);

	}

}
