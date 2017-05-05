package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.StringInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class StringInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        return new StringInfo(iterator.nextU2ToInt());
    }
}
