package jvm.classfile.attribute.parser.impl;

import jvm.classfile.attribute.item.AttributeInfo;
import jvm.classfile.attribute.item.impl.StackMapTableAttr;
import jvm.classfile.attribute.parser.AttributeInfoParser;
import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

/**
 * Created by Haochen on 2017/4/13.
 * TODO:
 */
public class StackMapTableParser implements AttributeInfoParser {
    @Override
    public AttributeInfo parse(int attrNameIndex, int attrLen,
                               ByteCodeIterator iterator, ConstantPool constantPool) {
        int index = iterator.nextU2ToInt();
        int len = iterator.nextU4ToInt();

        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iterator.nextHexString(len);

        return new StackMapTableAttr(index, len, code);
    }
}
