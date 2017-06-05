package com.coderising.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

/**
 * 一个Class文件的组合
 * 
 * @author Administrator
 *
 */
public class ClassFile {

	private int minorVersion;// 最小版本号

	private int majorVersion;// 最大版本号

	private AccessFlag accessFlag;// 访问标志

	private ClassIndex clzIndex;// 类索引

	private ConstantPool pool;// 常量池

	private List<Field> fields = new ArrayList<Field>();// 字段列表

	private List<Method> methods = new ArrayList<Method>();// 方法列表

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

	public void addField(Field f) {
		this.fields.add(f);
	}

	public List<Field> getFields() {
		return this.fields;
	}

	public void addMethod(Method m) {
		this.methods.add(m);
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void print() {

		if (this.accessFlag.isPublicClass()) {
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:" + getClassName());

		System.out.println("Super Class Name:" + getSuperClassName());

	}

	public String getClassName() {
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}

	public String getSuperClassName() {
		ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}

	public Method getMethod(String methodName, String paramAndReturnType) {

		return null;
	}

	public Method getMainMethod() {

		return null;
	}
}
