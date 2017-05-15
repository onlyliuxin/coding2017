package week08.jvm.cmd;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ConstantPool;
import week08.jvm.constant.MethodRefInfo;
import week08.jvm.engine.ExecutionResult;
import week08.jvm.engine.JavaObject;
import week08.jvm.engine.MethodArea;
import week08.jvm.engine.StackFrame;
import week08.jvm.method.Method;


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

		
	}

	
	

}
