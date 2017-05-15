package com.pan.jvm.print;

import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.constant.ClassInfo;
import com.pan.jvm.constant.ConstantInfo;
import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.constant.FieldRefInfo;
import com.pan.jvm.constant.MethodRefInfo;
import com.pan.jvm.constant.NameAndTypeInfo;

import com.pan.jvm.constant.StringInfo;
import com.pan.jvm.constant.UTF8Info;
import com.pan.jvm.loader.ClassFileLoader;

public class ClassFilePrinter {
	ClassFile clzFile = null;
	public ClassFilePrinter(ClassFile clzFile){
		this.clzFile = clzFile;
	}
	
	public void print(){
		
		if(clzFile.getAccessFlag().isPublicClass()){
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:"+ clzFile.getClassName());
		
		System.out.println("Super Class Name:"+ clzFile.getSuperClassName());
		
		System.out.println("minor version:" + clzFile.getMinorVersion());
		
		System.out.println("major version:" + clzFile.getMinorVersion());
		
		ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		cnstPoolPrinter.print();
		
		
		
		
	}
	
	public static void main(String[] args){
		String path = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\answer\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "com.pan.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
