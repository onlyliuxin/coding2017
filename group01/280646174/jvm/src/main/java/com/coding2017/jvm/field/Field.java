package com.coding2017.jvm.field;

import com.coding2017.jvm.constant.ConstantPool;
import com.coding2017.jvm.loader.ByteCodeIterator;

public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;

    private ConstantPool pool;

    public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {

        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.pool = pool;
    }

    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descriptorIndex = iter.nextU2ToInt();
        int attributeCount = iter.nextU2ToInt();
        if (attributeCount != 0) {
            throw new RuntimeException("not parse field attribute");
        }
        return new Field(accessFlag, nameIndex, descriptorIndex, pool);
    }

    @Override
    public String toString() {
        return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex);
    }
}
