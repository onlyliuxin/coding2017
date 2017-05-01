package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.StackFrame;

public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
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
