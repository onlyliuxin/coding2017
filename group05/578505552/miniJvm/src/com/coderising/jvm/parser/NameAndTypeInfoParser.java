package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by songbao.yang on 2017/4/19.
 */
public class NameAndTypeInfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
        nameAndTypeInfo.setIndex1(iterator.nextU2ToInt());
        nameAndTypeInfo.setIndex2(iterator.nextU2ToInt());

        return nameAndTypeInfo;
    }
}
