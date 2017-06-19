package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.MethodRefInfo;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.MethodArea;
import mini_jvm.engine.StackFrame;
import mini_jvm.method.Method;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsMethod();
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
				
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
