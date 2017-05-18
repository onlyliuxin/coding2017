package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.MethodRefInfo;
import jvm.classfile.method.Method;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.MethodArea;
import jvm.engine.StackFrame;
import jvm.exception.ReadClassException;

public class InvokeStaticCmd extends TwoOperandCmd {
    public InvokeStaticCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsMethod(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) throws ReadClassException {
        int methodIndex = (getOperand1() << 8) | getOperand2();
        MethodRefInfo methodRefInfo = (MethodRefInfo) getConstantInfo(methodIndex);
        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        Method method = MethodArea.getInstance().getMethod(methodRefInfo);
        result.setNextMethod(method);
    }
}
