package com.coding.mini_jvm.src.com.coderising.jvm.cmd;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.MethodRefInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.ExecutionResult;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.JavaObject;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.MethodArea;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.StackFrame;
import com.coding.mini_jvm.src.com.coderising.jvm.method.Method;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return super.getOperandAsMethod(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
		String methodName = methodRefInfo.getMethodName();
		String className = methodRefInfo.getClassName();
		if ("java/io/PrintStream".equals(className) && "println".equals(methodName)) {
			JavaObject jo = frame.getOprandStack().pop();
			System.out.println("***********"+jo.toString()+"***********");

			frame.getOprandStack().pop();
			return;
		}
		Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(nextMethod);
	}


}
