package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.classfile.field.Field;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.JavaObject;
import jvm.engine.MethodArea;
import jvm.engine.StackFrame;
import jvm.exception.ReadClassException;

public class PutStaticCmd extends TwoOperandCmd {

    public PutStaticCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) throws ReadClassException {
        int index = (getOperand1() << 8) | getOperand2();
        FieldRefInfo fieldRefInfo = (FieldRefInfo) getConstantInfo(index);
        ClassFile classFile = MethodArea.getInstance().findClassFile(fieldRefInfo.getClassName());
        String fieldName = fieldRefInfo.getNameAndType().split(":")[0];
        Field field = classFile.getField(fieldName);
        JavaObject object = frame.getOperandStack().pop();
        classFile.putStaticFieldValue(field.getName(), object);
    }
}
