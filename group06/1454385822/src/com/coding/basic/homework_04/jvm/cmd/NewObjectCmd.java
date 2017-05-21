package com.coding.basic.homework_04.jvm.cmd;

import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

//	@Override
//	public void execute(StackFrame frame, ExecutionResult result) {
//		
//		
//	}

	
}
