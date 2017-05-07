package cmd;

import clz.ClassFile;
import constant.ConstantPool;
import engine.FrameResult;
import engine.StackFrame;

/**
 * Created by william on 2017/4/17.
 */
public class GetFieldCmd extends TwoOperandCmd {
    public GetFieldCmd(ClassFile clzFile,String opCode) {
        super(clzFile,opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, FrameResult result) {

    }

}
