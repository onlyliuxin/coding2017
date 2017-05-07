package me.lzb.jvm.attr;

/**
 * Created by LZB on 2017/4/15.
 */
public class StackMapTable extends AttributeInfo {
    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen, String code) {
        super(attrNameIndex, attrLen);
        this.originalCode = code;
    }


}
