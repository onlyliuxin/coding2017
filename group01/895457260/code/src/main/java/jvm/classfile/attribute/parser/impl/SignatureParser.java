package jvm.classfile.attribute.parser.impl;

import jvm.classfile.ConstantPool;
import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.SignatureAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/30.
 * TODO:
 */
public class SignatureParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen, ByteCodeIterator iterator, ConstantPool constantPool) {
        int signatureIndex = iterator.nextU2ToInt();
        return new SignatureAttr(attrNameIndex, attrLen, signatureIndex);
    }
}
