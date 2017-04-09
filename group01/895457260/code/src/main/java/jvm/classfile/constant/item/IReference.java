package jvm.classfile.constant.item;

import jvm.classfile.ConstantPool;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public interface IReference {
    void linkReference(ConstantPool constantPool);
}
