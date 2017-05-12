package assignment0326.jvm.cmd;


import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.constant.ConstantPool;

public class BiPushCmd extends OneOperandCmd {

    public BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return this.getOffset() + ": " + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }


}
