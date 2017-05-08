package jvm.classfile.constant.item.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.IReference;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class ClassInfo implements Constant, IReference {
    private int nameIndex;
    private String className;

    public ClassInfo(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {
        Constant constant = constantPool.getConstantInfo(getUtf8Index());
        if (constant instanceof UTF8Info) {
            this.className = ((UTF8Info) constant).getValue();
        }
    }

    public int getUtf8Index() {
        return nameIndex;
    }

    public String getClassName() {
        return className;
    }
}
