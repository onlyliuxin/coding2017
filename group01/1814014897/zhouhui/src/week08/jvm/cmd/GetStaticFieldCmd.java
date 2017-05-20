package week08.jvm.cmd;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ConstantPool;
import week08.jvm.constant.FieldRefInfo;
import week08.jvm.engine.ExecutionResult;
import week08.jvm.engine.Heap;
import week08.jvm.engine.JavaObject;
import week08.jvm.engine.StackFrame;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
	
	}

}
