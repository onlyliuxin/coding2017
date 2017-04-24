package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 *
 * Created by songbao.yang on 2017/4/19.
 */
public class MethodRefInfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
        methodRefInfo.setClassInfoIndex(iterator.nextU2ToInt());
        methodRefInfo.setNameAndTypeIndex(iterator.nextU2ToInt());

        return methodRefInfo;
    }
}
