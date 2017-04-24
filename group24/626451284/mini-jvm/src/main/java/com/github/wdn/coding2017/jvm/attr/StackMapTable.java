package com.github.wdn.coding2017.jvm.attr;

import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class StackMapTable extends AttributeInfo{
    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ByteCodeIterator iter){
        int index = iter.readU2ToInt();
        int len = iter.readU4ToInt();
        StackMapTable t = new StackMapTable(index,len);
        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iter.readCustomToString(len);
        t.setOriginalCode(code);
        return t;
    }

    private void setOriginalCode(String code) {
        this.originalCode = code;

    }
}
