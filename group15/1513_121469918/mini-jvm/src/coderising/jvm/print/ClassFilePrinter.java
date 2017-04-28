package coderising.jvm.print;

import coderising.jvm.clz.ClassFile;
import coderising.jvm.constant.ClassInfo;
import coderising.jvm.constant.ConstantInfo;
import coderising.jvm.constant.ConstantPool;
import coderising.jvm.constant.FieldRefInfo;
import coderising.jvm.constant.MethodRefInfo;
import coderising.jvm.constant.NameAndTypeInfo;

import coderising.jvm.constant.StringInfo;
import coderising.jvm.constant.UTF8Info;
import coderising.jvm.loader.ClassFileLoader;

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
		String path = "C:\\Users\\qinwa\\Documents\\GitHub\\coding2017\\group15\\1513_121469918\\mini-jvm\\bin";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "coderising.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
