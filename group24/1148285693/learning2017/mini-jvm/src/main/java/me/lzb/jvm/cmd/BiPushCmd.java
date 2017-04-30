package me.lzb.jvm.cmd;


import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantPool;

public class BiPushCmd extends OneOperandCmd {

    public BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return this.getOffset() + ": " + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }


}
