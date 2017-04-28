package me.lzb.jvm.cmd;


import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.ConstantPool;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.Heap;
import me.lzb.jvm.engine.JavaObject;
import me.lzb.jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

    public BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return this.getOffset() + ": " + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }

    public String toString() {
        return toString(clzFile.getConstantPool());
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int value = this.getOperand();
        JavaObject jo = Heap.getInstance().newInt(value);
        frame.getOprandStack().push(jo);
    }
}
