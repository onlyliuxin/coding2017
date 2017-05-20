package com.coding.mini_jvm.src.com.coderising.jvm.cmd;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.MethodRefInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.ExecutionResult;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.MethodArea;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.StackFrame;
import com.coding.mini_jvm.src.com.coderising.jvm.method.Method;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
		if (methodRefInfo.getClassName().equals("java/lang/Object")
				&& methodRefInfo.getMethodName().equals("<init>")) {
			return;
		}
		Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(nextMethod);
	}


}
