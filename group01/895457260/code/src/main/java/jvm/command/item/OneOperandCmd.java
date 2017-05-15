package jvm.command.item;

import jvm.classfile.ClassFile;
import jvm.command.CommandIterator;

public abstract class OneOperandCmd extends ByteCodeCommand {

    protected int operand;

    public OneOperandCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    protected void initOperands(CommandIterator iterator) {
        setOperand(iterator.next2CharAsInt());
    }

    public int getOperand() {
        return this.operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }

    public int getLength() {
        return 2;
    }
}
