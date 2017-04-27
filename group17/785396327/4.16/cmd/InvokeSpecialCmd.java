package cmd;

import clz.ClassFile;
import constant.ConstantPool;
import engine.FrameResult;
import engine.StackFrame;

/**
 * Created by IBM on 2017/4/17.
 */
public class InvokeSpecialCmd extends TwoOperandCmd {
    public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
        super(clzFile,opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsMethod(pool);
    }

    @Override
    public void execute(StackFrame frame, FrameResult result) {

    }
}
