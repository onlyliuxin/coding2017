package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.command.CommandIterator;
import jvm.command.item.OneOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {
    public BiPushCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return this.getOffset() + ": " + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int op = getOperand();
        JavaObject object = Heap.getInstance().newInt(op);
        frame.getOperandStack().push(object);
    }
}
