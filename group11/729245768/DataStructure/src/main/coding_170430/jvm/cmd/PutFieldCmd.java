package main.coding_170430.jvm.cmd;

import main.coding_170430.jvm.clz.ClassFile;

/**
 * Created by peterchen on 2017/4/26.
 */
public class PutFieldCmd extends TwoOperandCmd {
    public PutFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile,opCode);
    }

    @Override
    public String toString() {

        return super.getOperandAsField();
    }

}
