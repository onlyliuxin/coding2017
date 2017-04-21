package jvm.print;

import jvm.ClassFileLoader;
import jvm.classfile.ClassFile;
import jvm.exception.ReadClassException;

import java.util.ArrayList;
import java.util.List;

public class ClassFilePrinter {
    ClassFile clzFile;

    public ClassFilePrinter(ClassFile clzFile) {
        this.clzFile = clzFile;
    }

    public void print() {
        if (clzFile.getAccessFlag().isPublicClass()) {
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name: " + clzFile.getClassName());
        System.out.println("Super Class Name: " + clzFile.getSuperClassName());
        System.out.println("Minor Version: " + clzFile.getMinorVersion());
        System.out.println("Major Version: " + clzFile.getMajorVersion());

        ConstantPoolPrinter poolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
        poolPrinter.print();
    }

    public static void main(String[] args) {
        String path = "target/test-classes";
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "jvm.EmployeeV1";

        ClassFile clzFile = null;
        try {
            clzFile = loader.load(className);
        } catch (ReadClassException e) {
            e.printStackTrace();
        }
        if (clzFile != null) {
            ClassFilePrinter printer = new ClassFilePrinter(clzFile);
            printer.print();
        }
    }
}
