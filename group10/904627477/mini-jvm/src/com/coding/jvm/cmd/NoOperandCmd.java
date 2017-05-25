package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	
	
	public  int getLength(){
		return 1;
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		
	}	

}
