package minijvm.print;

import java.io.File;

import minijvm.clz.ClassFile;
import minijvm.constant.ConstantInfo.Visitor;
import minijvm.loader.ClassFileLoader;

public class ClassFilePrinter {
    ClassFile clzFile = null;

    public ClassFilePrinter(ClassFile clzFile) {
        this.clzFile = clzFile;
    }

    public void print(Visitor visitor) {

        if (clzFile.getAccessFlag().isPublicClass()) {
            System.out.println("Access flag : public  ");
        }
        System.out.println("Class Name:" + clzFile.getClassName());

        System.out.println("Super Class Name:" + clzFile.getSuperClassName());

        System.out.println("minor version:" + clzFile.getMinorVersion());

        System.out.println("major version:" + clzFile.getMajorVersion());

        ConstantPoolPrinter cnstPoolPrinter = new ConstantPoolPrinter(clzFile.getConstantPool());
        cnstPoolPrinter.print(visitor);



    }

    public static void main(String[] args) throws ClassNotFoundException {
        String path = new File(".", "target\\test-classes").getAbsolutePath();
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "minijvm.loader.EmployeeV1";

        ClassFile clzFile = loader.loadClass(className);

        ClassFilePrinter printer = new ClassFilePrinter(clzFile);

        Visitor visitor = new JavapPrintVisitor();
        printer.print(visitor);
    }
}
