package me.lzb.jvm.constant;

import me.lzb.jvm.print.Print;
import me.lzb.jvm.print.PrintVisitor;

/**
 * @author LZB
 */
public abstract class ConstantInfo implements Print {

    public static final String MAGIC_NUMBER = "cafebabe";

    public static final int Class_info = 7;

    public static final int Fieldref_info = 9;

    public static final int Methodref_info = 10;

    public static final int String_info = 8;

    public static final int NameAndType_info = 12;

    public static final int Utf8_info = 1;


    protected ConstantPool constantPool;


    public ConstantInfo() {

    }

    public ConstantInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantPool.getConstantInfo(index);
    }


    public abstract int getType();


    @Override
    public abstract void print(PrintVisitor visitor);

}
