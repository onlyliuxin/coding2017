package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.classfile.constant.item.impl.InterfaceMethodRefInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class InterfaceMethodRefInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        int classIndex = ByteUtils.toInt(bytes, startIndex, 2);
        startIndex += 2;
        int nameAndTypeIndex = ByteUtils.toInt(bytes, startIndex, 2);
        return new InterfaceMethodRefInfo(classIndex, nameAndTypeIndex);
    }

    @Override
    public int length() {
        return 5;
    }
}
