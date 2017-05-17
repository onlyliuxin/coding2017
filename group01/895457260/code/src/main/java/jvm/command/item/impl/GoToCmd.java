package jvm.command.item.impl;

import jvm.classfile.ClassFile;
import jvm.classfile.ConstantPool;
import jvm.command.CommandIterator;
import jvm.command.item.TwoOperandCmd;
import jvm.engine.ExecutionResult;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;
import jvm.util.NumberUtils;

public class GoToCmd extends TwoOperandCmd {

    public GoToCmd(ClassFile clzFile, String opCode, CommandIterator iterator) {
        super(clzFile, opCode, iterator);
    }

    @Override
    public String toString(ConstantPool pool) {
        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int nextCmdOffset = (getOperand1() << 8) | getOperand2();
        nextCmdOffset = NumberUtils.toSignedInt(nextCmdOffset, 16);
        nextCmdOffset += getOffset();
        result.setNextCmdOffset(nextCmdOffset);
        result.setNextAction(ExecutionResult.JUMP);
    }
}
