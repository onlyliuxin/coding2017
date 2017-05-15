package miniJVM.cmd;

import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.MethodRefInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.MethodArea;
import miniJVM.engine.StackFrame;
import miniJVM.method.Method;

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
		MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(this.getIndex());

		if(methodRefInfo.getClassName().equals("java/lang/Object")
				&& methodRefInfo.getMethodName().equals("<init>")){
			return ;
		}
		Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);

		result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
		result.setNextMethod(nextMethod);
	}
}
