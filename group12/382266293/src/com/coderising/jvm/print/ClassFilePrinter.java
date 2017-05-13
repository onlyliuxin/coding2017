package com.coderising.jvm.print;

import java.io.UnsupportedEncodingException;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ClassFileLoader;

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
		
		System.out.println("major version:" + clzFile.getMajorVersion());
		
		ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		cnstPoolPrinter.print();
		
		
		
		
	}
	
	public static void main(String[] args){
		String path = "C:\\Users\\steve\\workspace\\coding2017n\\group12\\382266293\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "com.coderising.jvm.test.EmployeeV1";
		
		ClassFile clzFile = null;
		try {
			clzFile = loader.loadClass(className);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
