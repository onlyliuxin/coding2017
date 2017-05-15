package main.coding_170430.jvm.attr;

/**
 * Created by peter on 2017/4/21.
 */
public abstract  class AttributeInfo {
    public static final String CODE = "Code";
    public static final String CONST_VALUE = "ConstantValue";
    public static final String EXCPTIONS = "Exceptions";
    public static final String LINE_NUM_TABLE = "LineNumberTable";
    public static final String LOCAL_VAR_TABLE = "LocalVaribleTable";
    public static final String STACK_MAP_TABLE = "StackMapTable";

    int attrNameIndex;
    int attrLen;

    public AttributeInfo(int attrNameIndex,int attrLen){
        this.attrNameIndex = attrNameIndex;
        this.attrLen = attrLen;
    }
}
