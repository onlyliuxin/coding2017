package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.IntegerInfo;
import jvm.classfile.constant.parser.ConstantParser;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class IntegerInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        byte[] array = new byte[4];
        System.arraycopy(bytes, startIndex, array, 0, 4);
        return new IntegerInfo(array);
    }

    @Override
    public int length() {
        return 5;
    }
}
