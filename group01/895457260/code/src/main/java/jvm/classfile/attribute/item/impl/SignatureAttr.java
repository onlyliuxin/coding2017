package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

/**
 * Created by Haochen on 2017/4/30.
 * TODO:
 */
public class SignatureAttr extends AttributeInfo {
    private int signatureIndex;

    public SignatureAttr(int attrNameIndex, int attrLen, int signatureIndex) {
        super(attrNameIndex, attrLen);
        this.signatureIndex = signatureIndex;
    }
}
