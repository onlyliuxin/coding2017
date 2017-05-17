package task8.jvm.cmd;

import task8.jvm.clz.ClassFile;
import task8.jvm.constant.ConstantInfo;
import task8.jvm.constant.ConstantPool;
import task8.jvm.constant.MethodRefInfo;
import task8.jvm.engine.ExecutionResult;
import task8.jvm.engine.MethodArea;
import task8.jvm.engine.StackFrame;
import task8.jvm.method.Method;


public class InvokeSpecialCmd extends TwoOperandCmd {

    public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }

    @Override
    public String toString(ConstantPool pool) {

        return super.getOperandAsMethod(pool);
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        MethodRefInfo info = (MethodRefInfo) this.getConstantInfo(getIndex());

        if ("java/lang/Object".equalsIgnoreCase(info.getClassName()) && "<init>".equalsIgnoreCase(info.getMethodName())) {
            return;
        }
        Method method = MethodArea.getInstance().getMethod(info);
        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(method);
    }


}
