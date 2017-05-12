package main.coding_170430.jvm.print;

import main.coding_170430.jvm.constant.*;

/**
 * Created by peterchen on 2017/4/26.
 */
public class ConstantPoolPrinter {
    private ConstantPool pool;

    public ConstantPoolPrinter(ConstantPool pool) {
        this.pool = pool;
    }

    public void print() {
        for (int i = 1; i < pool.getSizes(); i++) {
            if (pool.getConstantInfo(i) instanceof ClassInfo) {
                System.out.println("#"+i+" = Class        "+"#"+((ClassInfo) pool.getConstantInfo(i)).getUtf8Index());
            } else if (pool.getConstantInfo(i) instanceof UTF8Info) {
                System.out.println("#"+i+" = Utf8        "+"#"+((UTF8Info) pool.getConstantInfo(i)).getValue());
            } else if (pool.getConstantInfo(i) instanceof StringInfo) {
                System.out.println("#"+i+" = String        "+"#"+((StringInfo) pool.getConstantInfo(i)).getIndex());
            } else if (pool.getConstantInfo(i) instanceof MethodRefInfo) {
                System.out.println("#"+i+" = Methodref        "+"#"+((MethodRefInfo) pool.getConstantInfo(i)).getClassInfoIndex()+".#"+((MethodRefInfo) pool.getConstantInfo(i)).getNameAndTypeIndex());
            } else if (pool.getConstantInfo(i) instanceof FieldRefInfo) {
                System.out.println("#"+i+" = Fieldref        "+"#"+((FieldRefInfo) pool.getConstantInfo(i)).getClassInfoIndex()+".#"+((FieldRefInfo) pool.getConstantInfo(i)).getNameAndTypeIndex());
            } else if (pool.getConstantInfo(i) instanceof NameAndTypeInfo) {
                System.out.println("#"+i+" = NameAndType        "+"#"+((NameAndTypeInfo) pool.getConstantInfo(i)).getIndexA()+":#"+((NameAndTypeInfo) pool.getConstantInfo(i)).getIndexB());
            } else {
                throw new RuntimeException("the constant has not implemented");
            }
        }

    }
}
