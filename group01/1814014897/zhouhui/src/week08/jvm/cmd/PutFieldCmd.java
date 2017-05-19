package week08.jvm.cmd;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ConstantPool;
import week08.jvm.engine.ExecutionResult;
import week08.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {

	}


}
