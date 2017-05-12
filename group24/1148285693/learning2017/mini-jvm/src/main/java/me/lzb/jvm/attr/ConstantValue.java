package me.lzb.jvm.attr;

import me.lzb.jvm.constant.ConstantPool;

/**
 * @author LZB
 * @date 2017/5/12
 */
public class ConstantValue {
    //U2
    private int attributeNameIndex;
    //U4
    private int attributeLength;

    //U2
    private int constantvalueIndex;

    private ConstantPool pool;

    public ConstantValue(int attributeNameIndex, int attributeLength, int constantvalueIndex, ConstantPool pool){
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
        this.constantvalueIndex = constantvalueIndex;
        this.pool = pool;
    }

}
