package cmd;

import clz.ClassFile;
import constant.ConstantPool;
import engine.FrameResult;
import engine.StackFrame;

/**
 * Created by gongxun on 2017/4/17.
 */
public class NewObjectCmd extends TwoOperandCmd {
    public NewObjectCmd(ClassFile clzFile, String opCode){
        super(clzFile,opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsClassInfo(pool);
    }

    @Override
    public void execute(StackFrame frame, FrameResult result) {

    }

}
