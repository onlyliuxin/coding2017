package com.github.chaoswang.learning.java.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.github.chaoswang.learning.java.jvm.constant.ClassInfo;
import com.github.chaoswang.learning.java.jvm.constant.ConstantPool;
import com.github.chaoswang.learning.java.jvm.field.Field;
import com.github.chaoswang.learning.java.jvm.method.Method;

public class ClassFile {

	//Class文件的次版本号
	private int minorVersion;
	//Class文件的主版本号,Java SE 6.0对应的虚拟机支持Java SE 5.0的编译器编译的Class文件结构，反之则不行
	private int majorVersion;

	/**
	 * Class文件中常量池主要存储了字 面量以及符号引用，其中字面量主要包括字符串，
	 * final常量的值或者某个属性的初始值等等，而符号引用主要存储类和接口的全限定名称，
	 * 字段的名称以及描 述符，方法的名称以及描述符
	 * CONSTANT_Utf8_info,CONSTANT_Float_info,CONSTANT_Double_info 
	 */
	private ConstantPool pool;
	
	//表示类或者接口的访问信息
	private AccessFlag accessFlag;
	//类的常量池索引、超类的索引
	private ClassIndex clzIndex;
	
	//字段表的信息
	private List<Field> fields = new ArrayList<Field>();
	//方法表
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

//		if (this.accessFlag.isPublicClass()) {
//			System.out.println("Access flag : public  ");
//		}
//		System.out.println("Class Name:" + getClassName());
//
//		System.out.println("Super Class Name:" + getSuperClassName());

	}

	private String getClassName() {
		int thisClassIndex = this.clzIndex.getThisClassIndex();
		ClassInfo thisClass = (ClassInfo) this.getConstantPool().getConstantInfo(thisClassIndex);
		return thisClass.getClassName();
	}

	private String getSuperClassName() {
		ClassInfo superClass = (ClassInfo) this.getConstantPool().getConstantInfo(this.clzIndex.getSuperClassIndex());
		return superClass.getClassName();
	}
}