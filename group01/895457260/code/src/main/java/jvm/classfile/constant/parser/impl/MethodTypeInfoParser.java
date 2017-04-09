package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.MethodTypeInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodTypeInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        int descriptorIndex = ByteUtils.toInt(bytes, startIndex, 2);
        return new MethodTypeInfo(descriptorIndex);
    }

    @Override
    public int length() {
        return 3;
    }
}
