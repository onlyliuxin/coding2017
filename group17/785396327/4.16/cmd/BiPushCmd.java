package cmd;

import clz.ClassFile;
import constant.ConstantPool;
import engine.ExecutionResult;
import engine.FrameResult;
import engine.StackFrame;

/**
 * Created by william on 2017/4/17.
 */
public class BiPushCmd extends OneOperandCmd {
    public BiPushCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return this.getOffset() + ": " + this.getOpCode() + " " + this.getReadableCodeText() + " " + this.getOperand();
    }

    @Override
    public void execute(StackFrame frame, FrameResult result) {

    }

}
