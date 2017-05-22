package me.lzb.jvm.attr;

/**
 * @author LZB
 */
public class StackMapTable extends AttributeInfo {
    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen, String code) {
        super(attrNameIndex, attrLen);
        this.originalCode = code;
    }


}
