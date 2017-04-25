package com.coding.basic.homework_04.jvm.cmd;

import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

//	@Override
//	public void execute(StackFrame frame, ExecutionResult result) {
//		
//	}

	
	

}
