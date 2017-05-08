package jvm.classfile.attribute.parser;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public interface AttributeInfoParser {
    AttributeInfo parse(int attrNameIndex, int attrLen,
                        ByteCodeIterator iterator, ConstantPool constantPool);
}
