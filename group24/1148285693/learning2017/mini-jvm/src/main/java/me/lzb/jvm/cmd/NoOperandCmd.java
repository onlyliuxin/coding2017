package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.Heap;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class NoOperandCmd extends ByteCodeCommand {

    public NoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    public String toString() {
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

        String opCode = this.getOpCode();

        if (ByteCodeCommand.aload_0.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(0);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.aload_1.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(1);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.aload_2.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(2);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.iload_1.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(1);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.iload_2.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(2);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.iload_3.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(3);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.fload_3.equals(opCode)) {

            JavaObject jo = frame.getLocalVariableValue(3);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.voidreturn.equals(opCode)) {

            result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);

        } else if (ByteCodeCommand.ireturn.equals(opCode)) {
            StackFrame callerFrame = frame.getCallerFrame();
            JavaObject jo = frame.getOprandStack().pop();
            callerFrame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.freturn.equals(opCode)) {

            StackFrame callerFrame = frame.getCallerFrame();
            JavaObject jo = frame.getOprandStack().pop();
            callerFrame.getOprandStack().push(jo);
        } else if (ByteCodeCommand.astore_1.equals(opCode)) {

            JavaObject jo = frame.getOprandStack().pop();

            frame.setLocalVariableValue(1, jo);

        } else if (ByteCodeCommand.dup.equals(opCode)) {

            JavaObject jo = frame.getOprandStack().peek();
            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.iconst_0.equals(opCode)) {

            JavaObject jo = Heap.getInstance().newInt(0);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.iconst_1.equals(opCode)) {

            JavaObject jo = Heap.getInstance().newInt(1);

            frame.getOprandStack().push(jo);

        } else if (ByteCodeCommand.istore_1.equals(opCode)) {

            JavaObject jo = frame.getOprandStack().pop();

            frame.setLocalVariableValue(1, jo);

        } else if (ByteCodeCommand.istore_2.equals(opCode)) {

            JavaObject jo = frame.getOprandStack().pop();

            frame.setLocalVariableValue(2, jo);

        } else if (ByteCodeCommand.iadd.equals(opCode)) {

            JavaObject jo1 = frame.getOprandStack().pop();
            JavaObject jo2 = frame.getOprandStack().pop();

            JavaObject sum = Heap.getInstance().newInt(jo1.getIntValue() + jo2.getIntValue());

            frame.getOprandStack().push(sum);

        } else if (ByteCodeCommand.aconst_null.equals(opCode)) {

            frame.getOprandStack().push(null);

        } else {
            throw new RuntimeException("you must forget to implement the operation :" + opCode);
        }

    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitNoOperandCmd(this);
    }


    public int getLength() {
        return 1;
    }

}
