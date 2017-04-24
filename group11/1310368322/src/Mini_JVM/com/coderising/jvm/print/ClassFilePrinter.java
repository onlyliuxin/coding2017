package com.coderising.jvm.print;

import java.io.IOException;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.loader.ClassFileLoader;

public class ClassFilePrinter {
	ClassFile clzFile = null;
	public ClassFilePrinter(ClassFile clzFile){
		this.clzFile = clzFile;
	}
	
	public void print(){
		
		
		/*System.out.println("Class Name:"+ clzFile.getClassName());
		
		System.out.println("Super Class Name:"+ clzFile.getSuperClassName());*/
		//System.out.println("public class " + clzFile.getConstantPool().getConstantInfo(clzFile.getClzIndex().getThisClassIndex()));
		
		System.out.println("minor version:" + clzFile.getMinorVersion());
		System.out.println("major version:" + clzFile.getMajorVersion());
		if(clzFile.getAccessFlag().isPublicClass()){
			System.out.println("flags: public  ");
		}
		ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		cnstPoolPrinter.print();

	}
	
	public static void main(String[] args) throws IOException{
		String path = "D:/ProgramWorld/Java/Practice/LangSi/2017编程提高群/bin/com/coderising/jvm/loader/EmployeeV1.class";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "com.coderising.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(path);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
