package com.github.wdn.coding2017.jvm.attr;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class AttributeInfo {
    public static final String CODE = "Code";
    public static final String CONST_VALUE = "ConstantValue";
    public static final String EXCEPTIONS = "Exceptions";
    public static final String LINE_NUM_TABLE = "LineNumberTable";
    public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
    public static final String STACK_MAP_TABLE = "StackMapTable";

    private int attributeNameIndex;// u2
    private int attributeLength; //u4
    public AttributeInfo(int attributeNameIndex,int attributeLength){
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }
}
