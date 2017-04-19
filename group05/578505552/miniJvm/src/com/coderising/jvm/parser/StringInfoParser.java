package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by songbao.yang on 2017/4/19.
 */
public class StringInfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        StringInfo stringInfo = new StringInfo(constantPool);
        stringInfo.setIndex(iterator.nextU2ToInt());

        return stringInfo;
    }
}
