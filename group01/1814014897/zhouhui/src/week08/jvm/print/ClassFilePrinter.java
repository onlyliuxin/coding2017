package week08.jvm.print;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ClassInfo;
import week08.jvm.constant.ConstantInfo;
import week08.jvm.constant.ConstantPool;
import week08.jvm.constant.FieldRefInfo;
import week08.jvm.constant.MethodRefInfo;
import week08.jvm.constant.NameAndTypeInfo;

import week08.jvm.constant.StringInfo;
import week08.jvm.constant.UTF8Info;
import week08.jvm.loader.ClassFileLoader;

public class ClassFilePrinter {
	ClassFile clzFile = null;

	public ClassFilePrinter(ClassFile clzFile) {
		this.clzFile = clzFile;
	}

	public void print() {

		if (clzFile.getAccessFlag().isPublicClass()) {
			System.out.println("Access flag : public  ");
		}
		System.out.println("Class Name:" + clzFile.getClassName());

		System.out.println("Super Class Name:" + clzFile.getSuperClassName());

		System.out.println("minor version:" + clzFile.getMinorVersion());

		System.out.println("major version:" + clzFile.getMinorVersion());

		ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
		cnstPoolPrinter.print();

	}

	public static void main(String[] args) {
		String path = ("" + ClassLoader.getSystemResource("")).replaceAll("file:/", "");
		ClassFileLoader loader = new ClassFileLoader();
		loader.addClassPath(path);
		String className = "week08.jvm.test.EmployeeV1";

		ClassFile clzFile = loader.loadClass(className);

		ClassFilePrinter printer = new ClassFilePrinter(clzFile);

		printer.print();
	}
}
