package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.Heap;
import org.xukai.jvm.engine.JavaObject;
import org.xukai.jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		JavaObject javaObject = Heap.getInstance().newInt(getOperand());
		frame.getOprandStack().push(javaObject);
	}


}
