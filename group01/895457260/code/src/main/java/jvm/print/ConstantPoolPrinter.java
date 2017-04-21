package jvm.print;

import jvm.classfile.ConstantPool;

public class ConstantPoolPrinter {
    ConstantPool pool;

    ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    public void print() {
        System.out.println("Constant Pool:");
    }
}
