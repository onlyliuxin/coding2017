package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.Utf8Info;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class Utf8InfoParser implements ConstantParser {
    private int length;

    public Utf8InfoParser(int length) {
        this.length = length;
    }

    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        startIndex += 2;
        byte[] array = new byte[length];
        System.arraycopy(bytes, startIndex, array, 0, length);
        return new Utf8Info(length, array);
    }

    @Override
    public int length() {
        return 3 + length;
    }
}
