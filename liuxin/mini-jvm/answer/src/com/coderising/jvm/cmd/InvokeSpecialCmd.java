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
	public String toString() {
		
		return super.getOperandAsMethod();
	}
	@Override
	public void execute(StackFrame frame,ExecutionResult result) {		
		
				
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(this.getIndex());
		
		// 我们不用实现jang.lang.Object 的init方法
		if(methodRefInfo.getClassName().equals("java/lang/Object") 
				&& methodRefInfo.getMethodName().equals("<init>")){
			return ;
			
		}
		Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);
		
		
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(nextMethod);
		
		
		
	}
	

}
