package com.donaldy.jvm.print;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ClassInfo;
import com.donaldy.jvm.constant.ConstantInfo;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.FieldRefInfo;
import com.donaldy.jvm.constant.MethodRefInfo;
import com.donaldy.jvm.constant.NameAndTypeInfo;

import com.donaldy.jvm.constant.StringInfo;
import com.donaldy.jvm.constant.UTF8Info;
import com.donaldy.jvm.loader.ClassFileLoader;

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
		String path = "D:\\tools\\Code\\Y_Repository\\coding2017\\group24\\448641125\\out\\production\\448641125\\";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "com.donaldy.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
