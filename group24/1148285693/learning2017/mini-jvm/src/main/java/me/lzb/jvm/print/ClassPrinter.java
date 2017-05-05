package me.lzb.jvm.print;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantInfo;
import me.lzb.jvm.constant.ConstantPool;

/**
 * Created by LZB on 2017/4/23.
 */
public class ClassPrinter {

    private ClassFile classFile;

    private ConstantPool pool;

    public ClassPrinter(ClassFile classFile) {
        this.classFile = classFile;
        this.pool = classFile.getConstantPool();
    }


    public void print() {
        PrintVisitor visitor = new PrintFormat();

        classFile.print(visitor);

        System.out.println("Constant Pool:");

        for (int i = 1; i <= pool.getSize(); i++) {
            ConstantInfo constantInfo = pool.getConstantInfo(i);
            System.out.print("#" + i + " = ");
            constantInfo.print(visitor);
        }
    }

}
