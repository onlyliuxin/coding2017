package com.coding.basic.homework_04.jvm.clz;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.homework_04.jvm.attr.AccessFlag;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.field.Field;
import com.coding.basic.homework_04.jvm.info.ClassInfo;
import com.coding.basic.homework_04.jvm.method.Method;

public class ClassFile {

	private String MagicNumber;	
	private int minorVersion;
	private int majorVersion;
	private int ConstantNum;
	
	private ConstantPool constantPool;
	private ClassIndex clzIndex;
	private AccessFlag accessFlag;
	private List<Field> fields = new ArrayList<Field>();
	private List<Method> methods = new ArrayList<Method>();
	
	
	public Method getMethod(String methodName, String paramAndReturnType){
		for(Method method : methods){
			String name = constantPool.getUTF8String(method.getNameIndex());
			String descriptor = constantPool.getUTF8String(method.getDescriptorIndex());
			if(name.equals(methodName) && descriptor.equals(paramAndReturnType)){
				return method;
			}
		}
		
		return null;
	}
	public Method getMainMethod(){
		for(Method method : methods){
			String name = constantPool.getUTF8String(method.getNameIndex());
			String descriptor = constantPool.getUTF8String(method.getDescriptorIndex());
			if(name.equals("main") && descriptor.equals("([Ljava/lang/String;)V")){
				return method;
			}
		}
		return null;
	}
	
	public void addMethod(Method method){
		methods.add(method);
	}
	
	public List<Method> getMethods() {
		return methods;
	}

	public void addField(Field field){
		fields.add(field);
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



	public int getConstantNum() {
		return ConstantNum;
	}



	public void setConstantNum(int constantNum) {
		ConstantNum = constantNum;
	}



	public void print() {
		System.out.println("MagicNumber: "+ MagicNumber);
		System.out.println( "minorVersion" +minorVersion);
		System.out.println("majorVersion" + majorVersion);
		
	}
	
	

	public String getMagicNumber() {
		return MagicNumber;
	}



	public void setMagicNumber(String magicNumber) {
		MagicNumber = magicNumber;
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


	public ConstantPool getConstantPool() {
		return constantPool;
	}


	public void setConstantPool(ConstantPool constantPool) {
		this.constantPool = constantPool;
	}



	public List<Field> getFields() {
		return fields;
	}
	
	public String getClassName() {
		ClassInfo clzInfo = (ClassInfo)constantPool.getConstantInfo(1);
		String clzName = clzInfo.getClassName();
		return clzName;
	}
	public String getSuperClassName() {
		ClassInfo clzInfo = (ClassInfo)constantPool.getConstantInfo(3);
		String clzName = clzInfo.getClassName();
		return clzName;
	}

	
}
