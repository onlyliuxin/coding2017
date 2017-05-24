package com.coderising.jvm.parser;

import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

import java.io.UnsupportedEncodingException;

/**
 *
 * Created by songbao.yang on 2017/4/19.
 */
public class UTF8InfoParser extends ConstantInfoParser {

    public ConstantInfo parser(ConstantPool constantPool, ByteCodeIterator iterator) {

        int lenght = iterator.nextU2ToInt();
        byte[] bytes = iterator.getBytes(lenght);

        String value = null;
        try {
            value = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        UTF8Info utf8Info = new UTF8Info(constantPool);
        utf8Info.setLength(lenght);
        utf8Info.setValue(value);

        return utf8Info;
    }
}
