package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.print.CommandPrinter;


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
		int immediateVal = this.getOperand();
		JavaObject obj = Heap.getInstance().newInt(immediateVal);
		System.out.println("push immediate value: " + obj.getIntValue() + " to operand stack.");
		frame.getOprandStack().push(obj);
		result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printBiPushCmd(this);
	}
	
	

}
