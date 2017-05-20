package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.ConstantPool;
import task8.jvm.engine.ExecutionResult;
import task8.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand {

    public NoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

    }


    public int getLength() {
        return 1;
    }

}
