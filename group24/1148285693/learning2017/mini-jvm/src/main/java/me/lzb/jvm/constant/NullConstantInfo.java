package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * Created by LZB on 2017/4/14.
 */
public class NullConstantInfo extends ConstantInfo{

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void print(PrintVisitor visitor) {

    }
}
