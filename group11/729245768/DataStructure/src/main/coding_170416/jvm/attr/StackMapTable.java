package main.coding_170416.jvm.attr;

import main.coding_170416.jvm.loader.ByteCodeIterator;

/**
 * Created by peter on 2017/4/21.
 */
public class StackMapTable extends AttributeInfo {
    private String originalCode;

    public StackMapTable(int attrNameIndex,int attrLen){
        super(attrNameIndex,attrLen);
    }
    public static StackMapTable parse(ByteCodeIterator iterator){
        int index = iterator.nextU2ToInt();
        int len = iterator.nextU4ToInt();
        StackMapTable t = new StackMapTable(index,len);
        return t;
    }
    private void setOriginalCode(String code){
        this.originalCode =code;
    }
}
