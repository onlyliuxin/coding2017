package me.lzb.jvm.cmd;

import me.lzb.jvm.clz.ClassFile;
import me.lzb.jvm.constant.MethodRefInfo;
import me.lzb.jvm.engine.ExecutionResult;
import me.lzb.jvm.engine.MethodArea;
import me.lzb.jvm.engine.StackFrame;
import me.lzb.jvm.method.Method;
import me.lzb.jvm.print.ExecutionVisitor;

/**
 * @author LZB
 */
public class InvokeSpecialCmd extends TwoOperandCmd {

    public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);

    }


    public String toString() {
        return super.getOperandAsMethod();
    }


    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());

        // 不实现jang.lang.Object 的init方法
        if (methodRefInfo.getClassName().equals("java/lang/Object")
            && methodRefInfo.getMethodName().equals("<init>")) {
            return;

        }
        Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);

        //设定执行结果和下一个方法
        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(nextMethod);
    }

    @Override
    public void printExecute(ExecutionVisitor visitor) {
        visitor.visitInvokeSpecialCmd(this);
    }


}
