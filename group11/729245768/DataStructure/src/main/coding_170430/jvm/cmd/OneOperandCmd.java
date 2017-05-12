package main.coding_170430.jvm.cmd;

import main.coding_170430.jvm.clz.ClassFile;

/**
 * Created by peterchen on 2017/4/26.
 */
public class OneOperandCmd extends ByteCodeCommand {
    private int operand;

    public OneOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }
    public  int getOperand() {

        return this.operand;
    }

    public void setOperand(int oprand1) {
        this.operand = oprand1;

    }
    public  int getLength(){
        return 2;
    }
}
