package jvm.command.item;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.command.CommandIterator;

public abstract class NoOperandCmd extends ByteCodeCommand {

    public NoOperandCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    protected void initOperands(CommandIterator iterator) {}

    @Override
    public String toString(ConstantPool pool) {
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText();
    }

    public int getLength() {
        return 1;
    }
}
