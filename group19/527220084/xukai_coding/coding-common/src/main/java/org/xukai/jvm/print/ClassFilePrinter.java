package org.xukai.jvm.print;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.loader.ClassFileLoader;

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
		String path = "D:\\java\\IDEA-Workspace\\coding2017\\group19\\527220084\\xukai_coding\\coding-common\\target\\classes";
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "org.xukai.jvm.test.EmployeeV1";
		
		ClassFile clzFile = loader.loadClass(className);
		
		ClassFilePrinter printer  = new ClassFilePrinter(clzFile);
		
		printer.print();
	}
}
