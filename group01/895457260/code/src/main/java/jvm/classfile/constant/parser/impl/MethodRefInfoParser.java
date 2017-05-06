package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class MethodRefInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        int classIndex = iterator.nextU2ToInt();
        int nameAndTypeIndex = iterator.nextU2ToInt();
        return new MethodRefInfo(classIndex, nameAndTypeIndex);
    }
}
