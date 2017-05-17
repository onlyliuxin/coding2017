package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class IncrementCmd extends TwoOperandCmd {

	public IncrementCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
		
	}

	@Override
	public String toString() {
		
		return this.getOffset()+":"+this.getOpCode()+ " " +this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		int index = this.getOprand1();
		
		int constValue = this.getOprand2();
		
		int currentValue = frame.getLocalVariableValue(index).getIntValue();
		
		JavaObject jo = Heap.getInstance().newInt(constValue+currentValue);
		
		frame.setLocalVariableValue(index, jo);
		

	}

}