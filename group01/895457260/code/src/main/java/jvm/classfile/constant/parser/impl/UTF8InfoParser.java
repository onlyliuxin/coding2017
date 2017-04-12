package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.UTF8Info;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class UTF8InfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        int length = iterator.nextU2ToInt();
        byte[] array = iterator.getBytes(length);
        return new UTF8Info(length, array);
    }
}
