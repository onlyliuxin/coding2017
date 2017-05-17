package com.github.chaoswang.learning.java.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.github.chaoswang.learning.java.jvm.constant.ClassInfo;
import com.github.chaoswang.learning.java.jvm.constant.ConstantPool;
import com.github.chaoswang.learning.java.jvm.field.Field;
import com.github.chaoswang.learning.java.jvm.method.Method;

public class ClassFile {

	//Class�ļ��Ĵΰ汾��
	private int minorVersion;
	//Class�ļ������汾��,Java SE 6.0��Ӧ�������֧��Java SE 5.0�ı����������Class�ļ��ṹ����֮����
	private int majorVersion;

	/**
	 * Class�ļ��г�������Ҫ�洢���� �����Լ��������ã�������������Ҫ�����ַ�����
	 * final������ֵ����ĳ�����Եĳ�ʼֵ�ȵȣ�������������Ҫ�洢��ͽӿڵ�ȫ�޶����ƣ�
	 * �ֶε������Լ��� �����������������Լ�������
	 * CONSTANT_Utf8_info,CONSTANT_Float_info,CONSTANT_Double_info 
	 */
	private ConstantPool pool;
	
	//��ʾ����߽ӿڵķ�����Ϣ
	private AccessFlag accessFlag;
	//��ĳ��������������������
	private ClassIndex clzIndex;
	
	//�ֶα����Ϣ
	private List<Field> fields = new ArrayList<Field>();
	//������
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