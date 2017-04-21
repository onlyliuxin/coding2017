package com.coding2017.jvm.attr;

import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo {

    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ClassFile clzFile, ByteCodeIterator iter, int nameIndex, int length) {
        StackMapTable t = new StackMapTable(nameIndex, length);

        // 后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iter.nextUxToHexString(length);
        t.setOriginalCode(code);

        return t;
    }

    private void setOriginalCode(String code) {
        this.originalCode = code;
    }
}
