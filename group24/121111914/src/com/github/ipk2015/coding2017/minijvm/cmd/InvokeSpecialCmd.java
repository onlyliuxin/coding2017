package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.MethodRefInfo;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.MethodArea;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;
import com.github.ipk2015.coding2017.minijvm.method.Method;

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
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(this.getIndex());
		// 我们不用实现jang.lang.Object 的init方法
		if(methodRefInfo.getClassName().equals("java/lang/Object") 
				&& methodRefInfo.getMethodName().equals("<init>")){
			return ;
			
		}
		Method method = MethodArea.getInstance().getMethod(methodRefInfo);
		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(method);
	}

	

}
