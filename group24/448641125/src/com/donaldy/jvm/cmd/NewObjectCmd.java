package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.engine.ExecutionResult;
import com.donaldy.jvm.engine.StackFrame;

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
