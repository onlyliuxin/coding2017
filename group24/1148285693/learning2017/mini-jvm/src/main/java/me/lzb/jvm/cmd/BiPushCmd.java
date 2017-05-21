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
public class BiPushCmd extends OneOperandCmd {

    public BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }


    public String toString() {
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int value = this.getOperand();
        JavaObject jo = Heap.getInstance().newInt(value);
        frame.getOprandStack().push(jo);
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitBiPushCmd(this);
    }
}
