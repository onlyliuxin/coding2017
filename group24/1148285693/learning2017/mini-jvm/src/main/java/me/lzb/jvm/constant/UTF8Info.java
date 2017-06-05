package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * @author LZB
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

    @Override
    public void print(PrintVisitor visitor) {
        visitor.visistUTF8(this);
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
