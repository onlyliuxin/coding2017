package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.DoubleInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class DoubleInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        byte[] high = iterator.getBytes(4);
        byte[] low = iterator.getBytes(4);
        return new DoubleInfo(high, low);
    }
}
