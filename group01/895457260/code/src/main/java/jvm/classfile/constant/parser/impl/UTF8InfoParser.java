package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.classfile.constant.parser.ConstantParser;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class UTF8InfoParser implements ConstantParser {
    private int length;

    public UTF8InfoParser(int length) {
        this.length = length;
    }

    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        startIndex += 2;
        byte[] array = new byte[length];
        System.arraycopy(bytes, startIndex, array, 0, length);
        return new UTF8Info(length, array);
    }

    @Override
    public int length() {
        return 3 + length;
    }
}
