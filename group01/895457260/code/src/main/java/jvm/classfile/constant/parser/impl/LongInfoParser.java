package jvm.classfile.constant.parser.impl;

import jvm.classfile.constant.item.Constant;
import jvm.classfile.constant.item.impl.DoubleInfo;
import jvm.classfile.constant.item.impl.LongInfo;
import jvm.classfile.constant.parser.ConstantParser;

/**
 * Created by Haochen on 2017/4/9.
 * TODO:
 */
public class LongInfoParser implements ConstantParser {
    @Override
    public Constant parse(byte[] bytes, int startIndex) {
        startIndex += TAG_LEN;
        byte[] high = new byte[4];
        byte[] low = new byte[4];
        for (int i = 0; i < 4; ++i) {
            high[i] = bytes[startIndex + i];
            low[i] = bytes[startIndex + i + 4];
        }
        return new LongInfo(high, low);
    }

    @Override
    public int length() {
        return 9;
    }
}
