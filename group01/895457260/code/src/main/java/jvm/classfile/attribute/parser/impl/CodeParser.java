package jvm.classfile.attribute.parser.impl;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.parser.AttributeParser;
import jvm.classfile.attribute.item.impl.CodeAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class CodeParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen,
                               ByteCodeIterator iterator, ConstantPool constantPool) {
        int maxStack = iterator.nextU2ToInt();
        int maxLocals = iterator.nextU2ToInt();
        int codeLen = iterator.nextU4ToInt();
        String code = iterator.nextHexString(codeLen);

        int exceptionTableLen = iterator.nextU2ToInt();
        iterator.skip(exceptionTableLen * 8);

        int attrCount = iterator.nextU2ToInt();
        List<AttributeInfo> attributes = new ArrayList<>();
        for (int i = 0; i < attrCount; ++i) {
            attributes.add(AttributeParser.parse(iterator, constantPool));
        }
        return new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, attributes);
    }
}
