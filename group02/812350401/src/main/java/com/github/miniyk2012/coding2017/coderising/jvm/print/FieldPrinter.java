package com.github.miniyk2012.coding2017.coderising.jvm.print;

import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.field.Field;
import com.github.miniyk2012.coding2017.coderising.jvm.loader.ClassFileLoader;

/**
 * Created by thomas_young on 6/5/2017.
 */
public class FieldPrinter {

    private ClassFile classFile;
    FieldPrinter(ClassFile classFile) {
        this.classFile = classFile;
    }

    public void print() {
        for (Field field: classFile.getFields()) {
            printSingleField(field);
            System.out.println();
        }
    }

    private void printSingleField(Field field) {
        String accessFlag = field.getAccessFlagDescription();
        String type = field.getType();
        String name = field.getName();
        System.out.println("  " + accessFlag.split("-")[0] + " " + type.replace("/", ".") + " " + name + ";");
        System.out.println("    descriptor: " + type);
        System.out.println("    flags: " + accessFlag.split("-")[1]);
    }

    public static void main(String[] args) {
        String path = ClassFilePrinter.class.getClassLoader().getResource("jvm").getPath();
        ClassFileLoader loader = new ClassFileLoader();
        loader.addClassPath(path);
        String className = "com.github.miniyk2012.coding2017.jvm.test.EmployeeV1";

        ClassFile clzFile = loader.loadClass(className);
        FieldPrinter fdPrinter = new FieldPrinter(clzFile);
        fdPrinter.print();
    }
}

