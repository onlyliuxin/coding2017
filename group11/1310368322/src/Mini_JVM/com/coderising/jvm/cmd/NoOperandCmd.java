package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	protected NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public int getLength() {
		return 1;
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		String opCode = this.getOpCode();
		
		
		
	}

}
