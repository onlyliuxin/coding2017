package com.coding.basic.homework_04.jvm.cmd;

import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

//	@Override
//	public void execute(StackFrame frame, ExecutionResult result) {
//		
//		
//	}

	
	

}
