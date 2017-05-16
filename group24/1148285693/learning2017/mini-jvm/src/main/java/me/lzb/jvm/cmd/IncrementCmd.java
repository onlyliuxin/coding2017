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
public class IncrementCmd extends TwoOperandCmd {

    public IncrementCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString() {

        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

        int index = this.getOprand1();

        int constValue = this.getOprand2();

        int currentValue = frame.getLocalVariableValue(index).getIntValue();

        JavaObject jo = Heap.getInstance().newInt(constValue + currentValue);

        frame.setLocalVariableValue(index, jo);


    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitIncrementCmd(this);
    }
}
