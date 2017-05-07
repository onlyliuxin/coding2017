package com.github.wdn.coding2017.jvm.clz;

import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class Field {
    private AccessFlag accessFlag;   // 例如是public , private 等等
    private int nameIndex;   // 指向常量池的入口
    private int descriptorIndex;   //指向常量池的入口
    private int attributesCount;   // 该字段的属性有多少个
    private ConstantPool pool;
    // attribute_info attributes[attributes_count];  //属性信息
    private Field(){
    }
    public Field(ConstantPool pool){
        this.pool = pool;
    }
    public static Field parse(ByteCodeIterator iter){
        Field field = new Field();
        field.setAccessFlags(new AccessFlag(iter.readU2ToInt()));
        field.setNameIndex(iter.readU2ToInt());
        field.setDescriptorIndex(iter.readU2ToInt());
        int attCount = iter.readU2ToInt();
        if(attCount>0){
            throw new RuntimeException("字段属性数量大于0");
        }
        field.setAttributesCount(attCount);
        return field;
    }
    public String toString(){
        return pool.getConstantInfo(nameIndex).getValue()+pool.getConstantInfo(descriptorIndex).getValue();
    }
    public AccessFlag getAccessFlags() {
        return accessFlag;
    }

    public void setAccessFlags(AccessFlag accessFlags) {
        this.accessFlag = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public void setPool(ConstantPool pool) {
        this.pool = pool;
    }
}
