package me.lzb.jvm.constant;

import me.lzb.jvm.print.PrintVisitor;

/**
 * @author LZB
 */
public class NullConstantInfo extends ConstantInfo {

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void print(PrintVisitor visitor) {

    }
}
