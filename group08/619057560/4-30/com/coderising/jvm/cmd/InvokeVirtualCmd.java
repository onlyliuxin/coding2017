package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.MethodArea;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.method.Method;


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
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(this.getIndex());
		
		String className = methodRefInfo.getClassName();
		String methodName = methodRefInfo.getMethodName();

		if("java/io/PrintStream".equals(className) && "println".equals(methodName)){
			JavaObject jo = (JavaObject)frame.getOprandStack().pop();
			String value = jo.toString();
			System.err.println("-------------------"+value+"----------------");
			frame.getOprandStack().pop();

			return;
		}
		
		JavaObject jo = frame.getOprandStack().peek();
		Method m = null;
		String currentClassName = jo.getClassName();
		while(currentClassName != null){
			ClassFile currentClassFile = MethodArea.getInstance().findClassFile(currentClassName);
			m = currentClassFile.getMethod(methodRefInfo.getMethodName(), 
					methodRefInfo.getParamAndReturnType());
			if(m != null){
				break;
			} else{
				currentClassName = currentClassFile.getSuperClassName();
			}
		}	
		
		if(m == null){
			throw new RuntimeException("Can't find method for :" + methodRefInfo.toString());
		}
		
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(m);
	}

	
	

}
