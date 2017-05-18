package week08.jvm.cmd;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ClassInfo;
import week08.jvm.constant.ConstantPool;
import week08.jvm.engine.ExecutionResult;
import week08.jvm.engine.Heap;
import week08.jvm.engine.JavaObject;
import week08.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {

		
	}

	
}
