package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.MethodRefInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.MethodArea;
import minijvm.engine.StackFrame;
import minijvm.method.Method;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsMethod();
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
        
        // java.lang.Object类的init方法（构造函数）不需要实现
        if ("java/lang/Object".equals(methodRefInfo.getClassName()) 
                && "<init>".equals(methodRefInfo.getMethodName())) {
            return;
        }
        
        Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);
        
        result.setNextAction(ExecutionResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(nextMethod);
    }

	

}
