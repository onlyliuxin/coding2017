package main.coding_170430.jvm.print;

import main.coding_170430.jvm.clz.ClassFile;

/**
 * Created by peterchen on 2017/4/26.
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

    public static void main(String[] args) {

    }
}
