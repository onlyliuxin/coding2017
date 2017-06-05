package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.MethodArea;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.method.Method;


public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}
	@Override
	public String toString() {
		ConstantPool pool=clzFile.getConstantPool();
		return toString(pool);
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		MethodRefInfo methodRefInfo=(MethodRefInfo)getConstantInfo(getIndex());
		if(methodRefInfo.getClassName().equals("java/lang/Object")&&methodRefInfo.getMethodName().equals("<init>")){
			return;
		}
		Method nextMethod=MethodArea.getInstance().getMethod(methodRefInfo);
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(nextMethod);
	}

	

}
