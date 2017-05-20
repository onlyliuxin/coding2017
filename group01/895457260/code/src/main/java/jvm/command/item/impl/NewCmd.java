package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.Constant;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

public class NewCmd extends TwoOperandCmd {

    public NewCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsClassInfo(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int index = (getOperand1() << 8) | getOperand2();
        Constant constant = getConstantInfo(index);
        JavaObject object = Heap.getInstance().createObject(constant);
        frame.getOperandStack().push(object);
    }
}
