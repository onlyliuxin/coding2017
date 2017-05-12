package main.coding_170423.jvm.cmd;

import main.coding_170423.jvm.clz.ClassFile;

/**
 * Created by peterchen on 2017/4/26.
 */
public class GetStaticFieldCmd extends TwoOperandCmd {
    public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile,opCode);

    }

    @Override
    public String toString() {

        return super.getOperandAsField();
    }

}
