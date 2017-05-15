package com.coderising.jvm.clasfile;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.JField;
import com.coderising.jvm.method.JMethod;

public class ClassFile {

	private int MinorVersion;
	private int MajorVersion;
	private String MagicNumer;
	private ConstantPool pool;
	private ClassIndex classIndex;
	private AccessFlag accessFlag;
	private List<JField> fields = new ArrayList<>();
	private List<JMethod> jMethods = new ArrayList<>();

	public ConstantPool getPool() {
		return pool;
	}

	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}

	public String getMagicNumer() {
		return MagicNumer;
	}

	public void setMagicNumer(String magicNumer) {
		this.MagicNumer = magicNumer;
	}

	public int getMinorVersion() {
		return MinorVersion;
	}

	public int getMajorVersion() {
		return MajorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.MinorVersion = minorVersion;
	}

	public void setMajorVersion(int majorVersion) {
		this.MajorVersion = majorVersion;
	}

	public ClassIndex getClassIndex() {
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

	public List<JField> getJFields() {
		return this.fields;
	}
	
	public void addJField(JField jField){
		this.fields.add(jField);
	}

	public List<JMethod> getMethods() {
		return this.jMethods;
	}
	
	public void addJMethod(JMethod jmethod){
		this.jMethods.add(jmethod);
	}

}
