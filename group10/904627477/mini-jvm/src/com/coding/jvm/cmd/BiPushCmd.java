package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ConstantInfo;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.Heap;
import com.coding.jvm.engine.JavaObject;
import com.coding.jvm.engine.StackFrame;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int value = this.getOperand();
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOprandStack().push(jo);
	}
	
}
