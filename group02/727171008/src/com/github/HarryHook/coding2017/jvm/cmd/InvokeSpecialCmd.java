package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.MethodRefInfo;
import com.github.HarryHook.coding2017.jvm.engine.ExecutionResult;
import com.github.HarryHook.coding2017.jvm.engine.MethodArea;
import com.github.HarryHook.coding2017.jvm.engine.StackFrame;
import com.github.HarryHook.coding2017.jvm.method.Method;

public class InvokeSpecialCmd extends TwoOperandCmd {

    public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
	super(clzFile, opCode);

    }

    @Override
    public String toString() {

	return super.getOperandAsMethod();
    }

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {

	MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());

	// 我们不用实现jang.lang.Object 的init方法
	if (methodRefInfo.getClassName().equals("java/lang/Object") && methodRefInfo.getMethodName().equals("<init>")) {
	    return;

	}
	Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);

	result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
	result.setNextMethod(nextMethod);

    }

}