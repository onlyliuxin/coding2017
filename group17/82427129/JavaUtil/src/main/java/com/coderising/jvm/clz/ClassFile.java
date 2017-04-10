package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

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

	public ConstantPool getPool() {
		return pool;
	}

	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void addFields(Field f){
		this.fields.add(f);
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void addMethods(Method m){
		this.methods.add(m);
	}

}
