package me.lzb.jvm.attr;

/**
 * @author LZB
 * @date 2017/5/12
 */
public class ConstantValue extends AttributeInfo {

    private int constValueIndex;

    public ConstantValue(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }


}
