package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantInfo;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.MethodRefInfo;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.MethodArea;
import org.xukai.jvm.engine.StackFrame;
import org.xukai.jvm.method.Method;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int index = getIndex();
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(index);
		String methodName = methodRefInfo.getMethodName();
		String paramAndReturnType = methodRefInfo.getParamAndReturnType();
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
