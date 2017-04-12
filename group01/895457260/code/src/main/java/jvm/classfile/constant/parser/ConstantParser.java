package jvm.classfile.constant.parser;

import jvm.classfile.constant.item.Constant;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public interface ConstantParser {
    Constant parse(ByteCodeIterator iterator);
}
