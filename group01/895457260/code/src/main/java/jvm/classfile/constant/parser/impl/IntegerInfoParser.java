package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.IntegerInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class IntegerInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        return new IntegerInfo(iterator.getBytes(4));
    }
}
