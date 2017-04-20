package com.coderising.jvm.print;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.exception.NotAClassFileException;
import com.coderising.jvm.loader.ClassFileLoader;

public class ClassFilePrinter {
	
	ClassFile clzFile = null;
	public ClassFilePrinter(ClassFile clzFile){
		this.clzFile = clzFile;
	}
	
	public void print(){
		
		if(clzFile.getAccessFlag().isPublicClass()){
			System.out.println("Access flag:public  ");
		}
		System.out.println("Class Name:"+ clzFile.getClassName());
		
		System.out.println("Super ClassName:"+ clzFile.getSuperClassName());
		
		System.out.println("minor version:" + clzFile.getMinorVersion());
		
		System.out.println("major version:" + clzFile.getMinorVersion()+"\n");
		
		ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		cnstPoolPrinter.print();
		
		
		
		
	}
	
	public static void main(String[] args){
		String path = "F:\\githubRes\\coding2017\\group12\\2258659044\\zj-2017\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "test.com.coderising.jvm.EmployeeV1";
		
		ClassFile clzFile = null;
		try {
			clzFile = loader.loadClass(className);
		} catch (NotAClassFileException e) {
			e.printStackTrace();
		}
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}