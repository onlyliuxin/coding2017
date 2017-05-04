package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.NameAndTypeInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class NameAndTypeInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        int nameIndex = iterator.nextU2ToInt();
        int descriptorIndex = iterator.nextU2ToInt();
        return new NameAndTypeInfo(nameIndex, descriptorIndex);
    }
}
