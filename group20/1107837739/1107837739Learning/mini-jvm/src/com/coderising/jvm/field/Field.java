package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

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

        System.out.println("Filed accessFlag: " + accessFlag);
        System.out.println("Filed nameIndex: " + nameIndex);
        System.out.println("Filed descriptorIndex: " + descriptorIndex);
        System.out.println("Filed attributeCount: " + attributeCount);

        if (attributeCount > 0) {
            throw new RuntimeException("Filed attribute hasn't been implemented yet");
        }

        return new Field(accessFlag, nameIndex, descriptorIndex, pool);
    }

    @Override
    public String toString() {
        String name = pool.getUTF8String(this.nameIndex);
        String desc = pool.getUTF8String(this.descriptorIndex);

        return name + ":" + desc;
    }
}
