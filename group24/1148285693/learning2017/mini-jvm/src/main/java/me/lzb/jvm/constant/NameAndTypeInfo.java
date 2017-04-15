package me.lzb.jvm.constant;

/**
 * Created by LZB on 2017/4/15.
 */
public class NameAndTypeInfo extends ConstantInfo {
    private int type = ConstantInfo.NameAndType_info;

    private int index1;
    private int index2;

    public NameAndTypeInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getType() {
        return type;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }
}
