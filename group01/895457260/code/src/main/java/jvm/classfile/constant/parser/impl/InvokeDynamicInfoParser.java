package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.InvokeDynamicInfo;
import jvm.classfile.constant.parser.ConstantParser;
import jvm.util.ByteCodeIterator;
import jvm.util.ByteUtils;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class InvokeDynamicInfoParser implements ConstantParser {
    @Override
    public Constant parse(ByteCodeIterator iterator) {
        int bootstrapMethodAttrIndex = iterator.nextU2ToInt();
        int nameAndTypeIndex = iterator.nextU2ToInt();
        return new InvokeDynamicInfo(bootstrapMethodAttrIndex, nameAndTypeIndex);
    }
}
