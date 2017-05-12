package assignment0326.jvm.cmd;


import assignment0326.jvm.clz.ClassFile;
import assignment0326.jvm.constant.ConstantPool;

public class NewObjectCmd extends TwoOperandCmd {

    public NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsClassInfo(pool);
    }


}
