package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFile {

	private int minorVersion;
	private int MajorVersion;
	private ConstantPool constantPool;
	private ClassIndex classIndex;
	private AccessFlag accessFlag;
	private List<Field> fields = new ArrayList<Field> ();
	private List<Method> methods = new ArrayList<Method> ();
	
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

	public List<Field> getFields() {
		return this.fields;
	}
	
	public void addFiled(Field field) {
		this.fields.add(field);
	}

	public List<Method> getMethods() {
		return this.methods;
	}
	
	public void addMethod(Method method) {
		this.methods.add(method);
	}

	public Method getMethod(String methodName, String paramterType) {
		for (Method method: this.methods) {
			if(getConstantPool().getUTF8String(method.getNameIndex()).equals(methodName) 
				&& getConstantPool().getUTF8String(method.getDescIndex()).equals(paramterType)) {
				return method;
			}
		}
		return null;
	}

	public Method getMainMethod() {
		return this.getMethod("main", "([Ljava/lang/String;)V");
	}
	
}
