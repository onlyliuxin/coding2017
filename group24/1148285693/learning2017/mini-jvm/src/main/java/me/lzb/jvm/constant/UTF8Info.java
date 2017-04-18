package me.lzb.jvm.constant;

/**
 * Created by LZB on 2017/4/15.
 */
public class UTF8Info extends ConstantInfo {
    private int type = ConstantInfo.Class_info;

    private String value;

    private int length;

    public UTF8Info(ConstantPool pool) {
        super(pool);
    }


    @Override
    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
