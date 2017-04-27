package print;

import clz.ClassFile;
import jvm_1.ClassFileLoader;

/**
 * Created by gongxun on 2017/4/21.
 */
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
        String path = "G:\\Git\\homework\\coding2017\\group17\\785396327\\out\\production\\785396327";
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "jvm_1.EmployeeV1";

        ClassFile clzFile = loader.loadClass(className);

        ClassFilePrinter printer  = new ClassFilePrinter(clzFile);

        printer.print();
    }
}
