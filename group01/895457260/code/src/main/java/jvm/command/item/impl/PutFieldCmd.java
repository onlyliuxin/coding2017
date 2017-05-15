package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

    public PutFieldCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        JavaObject value = frame.getOperandStack().pop();
        JavaObject obj = frame.getOperandStack().pop();
        int fieldIndex = (getOperand1() << 8) | getOperand2();
        FieldRefInfo fieldRefInfo = (FieldRefInfo) getConstantInfo(fieldIndex);
        String fieldName = fieldRefInfo.getNameAndType().split(":")[0];
        obj.setFieldValue(fieldName, value);
    }
}
