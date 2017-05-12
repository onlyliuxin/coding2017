package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class SourceFileAttr extends AttributeInfo {
    private int sourceFileIndex;

    public SourceFileAttr(int attrNameIndex, int attrLen, int sourceFileIndex) {
        super(attrNameIndex, attrLen);
        this.sourceFileIndex = sourceFileIndex;
    }
}
