package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.method.Method;
import com.coderising.jvm.print.CommandPrinter;


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
		//System.out.println(frame);
		
		
		MethodRefInfo methodRef = (MethodRefInfo) this.getConstantInfo(this.getIndex());
		System.out.println("invoke new method  " + methodRef);
		String methodName = methodRef.getMethodName();
		if(methodRef.getClassName().equals("java/lang/Object") && methodRef.getMethodName().equals("<init>"))
		{
			System.out.println("The creation of Java object has been skipped.");
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			return; //skipping the creation of java/lang/object.
		}
		
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		String parameterAndReturn = methodRef.getParamAndReturnType();
		Method nextMethod = this.clzFile.getMethod(methodName, parameterAndReturn);
		result.setNextMethod(nextMethod);
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printInvokeSpecialCmd(this);
	}

	

}
