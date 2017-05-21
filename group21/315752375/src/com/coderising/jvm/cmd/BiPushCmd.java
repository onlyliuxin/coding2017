package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
		ConstantPool pool=clzFile.getConstantPool();
		return toString(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		int operand=getOperand();
		JavaObject javaObject=Heap.getInstance().newInt(operand);
		frame.getOprandStack().push(javaObject);
		
	}

	@Override
	public String toString(ConstantPool constantPool) {
		// TODO Auto-generated method stub
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
