package jvm.classfile.constant.item.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.IReference;

import java.util.Map;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodHandleInfo implements Constant, IReference {
    private int referenceKind;
    private int referenceIndex;

    public MethodHandleInfo(int referenceKind, int referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public Map<Integer, String> printableMap() {
        return null;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    @Override
    public void linkReference(ConstantPool constantPool) {

    }
}
