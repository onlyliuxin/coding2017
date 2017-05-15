package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.classfile.field.Field;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.*;
import jvm.exception.ReadClassException;

public class GetStaticCmd extends TwoOperandCmd {

    public GetStaticCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
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
        JavaObject object = classFile.getStaticFieldValue(field.getName());
        frame.getOperandStack().push(object);
    }
}
