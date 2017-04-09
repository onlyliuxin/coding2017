package jvm.classfile.constant.parser;

import jvm.classfile.constant.item.Constant;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public interface ConstantParser {
    int TAG_LEN = 1;

    Constant parse(byte[] bytes, int startIndex);
    int length();
}
