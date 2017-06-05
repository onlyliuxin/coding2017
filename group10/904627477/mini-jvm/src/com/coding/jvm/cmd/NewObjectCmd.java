package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.StackFrame;

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
		// TODO Auto-generated method stub
		
	}

	
}
