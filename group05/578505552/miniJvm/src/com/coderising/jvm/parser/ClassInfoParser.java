package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 *
 * Created by songbao.yang on 2017/4/19.
 */
public class ClassInfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        ClassInfo classInfo = new ClassInfo(constantPool);
        int index = iterator.nextU2ToInt();
        classInfo.setUtf8Index(index);

        return classInfo;
    }
}
