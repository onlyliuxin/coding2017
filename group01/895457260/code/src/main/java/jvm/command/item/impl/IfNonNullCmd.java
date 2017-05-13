package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.classfile.constant.item.impl.FieldRefInfo;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;
import jvm.util.NumberUtils;

public class IfNonNullCmd extends TwoOperandCmd {

    public IfNonNullCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        JavaObject obj = frame.getOperandStack().pop();
        if (obj != null) {
            int nextCmdOffset = (getOperand1() << 8) | getOperand2();
            nextCmdOffset = NumberUtils.toSignedInt(nextCmdOffset, 16);
            nextCmdOffset += getOffset();
            result.setNextCmdOffset(nextCmdOffset);
            result.setNextAction(ExecutionResult.JUMP);
        }
    }
}
