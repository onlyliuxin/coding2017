package week7.jvm.print;


import week7.jvm.clz.ClassFile;
import week7.jvm.loader.ClassFileLoader;

public class ClassFilePrinter {

	private ClassFile clzFile;
	
	public ClassFilePrinter(ClassFile clzFile){
		this.clzFile=clzFile;
	}
	
	public void print(){
		
		if(clzFile.getAccessFlag().isFinalClass()){
			System.out.println("Access flag : public  ");
		}
		
		System.out.println("Class Name:"+clzFile.getClassName());
		System.out.println("Super Class Name:"+clzFile.getSuperClassName());
		
		System.out.println("Minor Version:"+clzFile.getMinorVersion());
		System.out.println("Major Version:"+clzFile.getMajorVersion());
		
		ConstantPoolPrinter constantPrinter=new ConstantPoolPrinter(clzFile.getConstantPool());	
		constantPrinter.print();
	}
	
	public static void main(String[] args){
	      String path="E:\\JAVA\\liuxin\\coding2017\\group26\\1515345281\\bin";
	      String className = "week5.jvm.test.EmployeeV1";
	      
	      ClassFileLoader loader=new ClassFileLoader();
	      
	      loader.addClassPath(path);
	      
	      ClassFile clzFile=loader.loadClass(className);
	     
	      ClassFilePrinter clzFilePrinter=new ClassFilePrinter(clzFile);
	      clzFilePrinter.print();
	}
}
