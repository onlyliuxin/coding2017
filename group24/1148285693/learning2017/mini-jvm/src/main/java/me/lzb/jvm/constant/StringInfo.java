package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * Created by LZB on 2017/4/15.
 */
public class StringInfo extends ConstantInfo {
    private int type = ConstantInfo.String_info;

    private int index;

    public StringInfo(ConstantPool pool) {
        super(pool);
    }
    @Override
    public int getType() {
        return type;
    }

    @Override
    public void print(PrintVisitor visitor) {
        visitor.visitString(this);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
