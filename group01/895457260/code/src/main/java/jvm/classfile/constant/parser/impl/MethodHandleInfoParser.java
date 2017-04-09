package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.MethodHandleInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodHandleInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        int referenceKind = ByteUtils.toInt(bytes, startIndex, 1);
        startIndex += 1;
        int referenceIndex = ByteUtils.toInt(bytes, startIndex, 2);
        return new MethodHandleInfo(referenceKind, referenceIndex);
    }

    @Override
    public int length() {
        return 4;
    }
}
