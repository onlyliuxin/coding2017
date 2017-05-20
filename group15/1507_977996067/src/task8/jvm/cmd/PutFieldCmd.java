package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.ConstantPool;
import task8.jvm.engine.ExecutionResult;
import task8.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

    public PutFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsField(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

    }


}
