package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * Created by xudanxia on 2017/4/11.
 */
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

    public String toString() {
        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }

    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {

        int accessFlag = iter.nextU2toInt();
        int nameIndex = iter.nextU2toInt();
        int descIndex = iter.nextU2toInt();
        int attrCount = iter.nextU2toInt();
        System.out.println("Attribute Count: " + attrCount);
        if (attrCount > 0) {
            throw new RuntimeException("没有实现字段属性!");
        }
        return new Field(accessFlag, nameIndex, descIndex, pool);
    }
}
