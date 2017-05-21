package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 *
 * Created by songbao.yang on 2017/4/19.
 */
public abstract class ConstantInfoParser {

    public abstract ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator);
}
