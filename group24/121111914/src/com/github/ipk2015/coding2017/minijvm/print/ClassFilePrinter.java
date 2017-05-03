package com.github.ipk2015.coding2017.minijvm.print;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.loader.ClassFileLoader;

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
//		String path = "C:\\Users\\liuxin\\git\\coding2017\\liuxin\\mini-jvm\\bin";
		String path = "E:\\javaImprove\\git\\group24\\121111914\\src\\com\\github\\ipk2015\\coding2017\\minijvm\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
//		String className = "com.coderising.jvm.test.EmployeeV1";
		String className = "EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
