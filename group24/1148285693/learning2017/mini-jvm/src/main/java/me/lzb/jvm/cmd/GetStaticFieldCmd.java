package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantPool;

public class GetStaticFieldCmd extends TwoOperandCmd {

    public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsField(pool);
    }

}
