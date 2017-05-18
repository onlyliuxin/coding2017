package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by songbao.yang on 2017/4/19.
 */
public class FieldRefInfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
        fieldRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
        fieldRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());

        return fieldRefInfo;
    }
}
