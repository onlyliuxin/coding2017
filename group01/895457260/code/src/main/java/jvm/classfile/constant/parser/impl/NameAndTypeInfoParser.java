package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.NameAndTypeInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class NameAndTypeInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        int nameIndex = ByteUtils.toInt(bytes, startIndex, 2);
        startIndex += 2;
        int descriptorIndex = ByteUtils.toInt(bytes, startIndex, 2);
        return new NameAndTypeInfo(nameIndex, descriptorIndex);
    }

    @Override
    public int length() {
        return 5;
    }
}
