package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
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

    @Override
    public String toString() {
        String name = pool.getUTF8String(nameIndex);
        String descr = pool.getUTF8String(descriptorIndex);

        return name+":"+descr;
    }

    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2Int();
        int nameIndex = iter.nextU2Int();
        int descriptorIndex = iter.nextU2Int();

        int attributsCount = iter.nextU2Int();

        if (attributsCount > 0) {
            throw new RuntimeException("field : " + ((UTF8Info) pool.getConstantInfo(nameIndex)).getValue() + "has attributes to be implemented !!");
        }

        Field field = new Field(accessFlag,nameIndex,descriptorIndex,pool);
        return field;
    }

}
