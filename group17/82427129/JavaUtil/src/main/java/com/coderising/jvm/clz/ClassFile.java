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

	public void print() {
		if (this.accessFlag.isPublicClass()) {
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:" + getClassName());

		System.out.println("Super Class Name:" + getSuperClassName());
	}

	public String getClassName() {
		int index = this.clzIndex.getThisClassIndex();
		ClassInfo c = (ClassInfo) pool.getConstantInfo(index);
		return c.getClassName();
	}

	public String getSuperClassName() {
		int index = this.clzIndex.getSuperClassIndex();
		ClassInfo c = (ClassInfo) pool.getConstantInfo(index);
		return c.getClassName();
	}

	public void addFields(Field f) {
		this.fields.add(f);
	}

	public void addMethods(Method m) {
		this.methods.add(m);
	}

	/*
	 * getter setter
	 */
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

	public void setClzIndex(ClassIndex clzIndex) {
		this.clzIndex = clzIndex;
	}

	public ConstantPool getConstantPool() {
		return pool;
	}

	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fl) {
		this.fields = fl;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> ml) {
		this.methods = ml;
	}

}
