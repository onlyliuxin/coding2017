package com.coding.mini_jvm.src.com.coderising.jvm.cmd;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.ExecutionResult;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.JavaObject;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		if (CommandParser.astore_1.equals(this.getOpCode())) {
			JavaObject jo = frame.getOprandStack().pop();
			frame.setLocalVariableValue(1, jo);
		} else if (CommandParser.aload_0.equals(this.getOpCode())) {
			JavaObject jo = frame.getLocalVariableValue(0);
			frame.getOprandStack().push(jo);
		} else if (CommandParser.aload_1.equals(this.getOpCode())) {
			JavaObject jo = frame.getLocalVariableValue(1);
			frame.getOprandStack().push(jo);
		} else if (CommandParser.iload_1.equals(this.getOpCode())) {
			JavaObject jo = frame.getLocalVariableValue(1);
			frame.getOprandStack().push(jo);
		} else if (CommandParser.iload_2.equals(this.getOpCode())) {
			JavaObject jo = frame.getLocalVariableValue(2);
			frame.getOprandStack().push(jo);
		} else if (CommandParser.istore_1.equals(this.getOpCode())) {
			JavaObject jo = frame.getOprandStack().pop();
			frame.setLocalVariableValue(1, jo);
		} else if (CommandParser.voidreturn.equals(this.getOpCode())) {
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
		} else if (CommandParser.dup.equals(this.getOpCode())) {
			JavaObject jo = frame.getOprandStack().peek();
			frame.getOprandStack().push(jo);
		} else {
			throw new RuntimeException("this operator ["+this.getOpCode()+"] not support yet");
		}
	}


	public  int getLength(){
		return 1;
	}

}
