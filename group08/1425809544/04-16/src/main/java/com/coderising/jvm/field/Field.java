package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;

/**
 * 字段
 *
 * @author xyy
 * @create 2017-04-14 17:17
 **/
public class Field {

    private int accessFlag;
    private int descriptorIndex;
    private int nameIndex;


    private ConstantPool pool;

    public Field(int accessFlag, int nameIndex,int descriptorIndex,  ConstantPool pool) {
        this.accessFlag = accessFlag;
        this.descriptorIndex = descriptorIndex;
        this.nameIndex = nameIndex;
        this.pool = pool;
    }


    //解析常量池
    public static Field parse(ConstantPool pool, ByteCodeIterator iterator) {

        int accessFlag = iterator.nextU2ToInt();
        int nameIndex = iterator.nextU2ToInt();
        int descriptorIndex = iterator.nextU2ToInt();
        int attributesCount = iterator.nextU2ToInt();
        Field field = new Field(accessFlag, nameIndex, descriptorIndex, pool);

        if (attributesCount > 0) {
            throw new RuntimeException("Field Attribute has not been implemented");
        }
        return field;
    }

    public String toString() {
        String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();

        String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
        return name +":"+ desc;
    }
}
