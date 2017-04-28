package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantPool;

public class InvokeVirtualCmd extends TwoOperandCmd {

    public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsMethod(pool);
    }


}
