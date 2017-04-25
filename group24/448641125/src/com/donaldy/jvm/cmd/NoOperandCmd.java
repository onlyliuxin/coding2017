package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.engine.ExecutionResult;
import com.donaldy.jvm.engine.StackFrame;

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


	}


}
