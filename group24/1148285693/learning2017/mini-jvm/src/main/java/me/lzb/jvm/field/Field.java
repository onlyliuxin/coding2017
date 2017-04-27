package me.lzb.jvm.field;

import me.lzb.jvm.constant.ConstantPool;

/**
 * Created by LZB on 2017/4/15.
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private ConstantPool pool;

    public Field(int accessFlag, int nameIndex, int descriptorIndex ,ConstantPool pool) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.pool = pool;
    }


    @Override
    public String toString() {
        String key = pool.getUTF8String(nameIndex);
        String value = pool.getUTF8String(descriptorIndex);
        return key + ":" + value;
    }
}
