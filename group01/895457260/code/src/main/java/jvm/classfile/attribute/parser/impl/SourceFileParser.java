package jvm.classfile.attribute.parser.impl;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.SourceFileAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class SourceFileParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen,
                               ByteCodeIterator iterator, ConstantPool constantPool) {
        int sourceFileIndex = iterator.nextU2ToInt();
        return new SourceFileAttr(attrNameIndex, attrLen, sourceFileIndex);
    }
}
