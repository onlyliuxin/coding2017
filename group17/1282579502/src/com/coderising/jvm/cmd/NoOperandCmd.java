package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.print.CommandPrinter;

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
		switch(this.getReadableCodeText().toLowerCase()){
		case "return":
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
			break;
		case "ireturn":
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
			break;
		case "freturn": 
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
			break;
		case "dup": 
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			JavaObject obj = frame.getOprandStack().peek();
			frame.getOprandStack().push(obj);
			System.out.println("duplicated java object on stack top: " + obj);
			break;
		case "aload_0":
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			frame.getOprandStack().push(frame.getLocalVariableValue(0));
			System.out.println("pushed 0th ref item on to operand.");
			break;
		case "aload_1":
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			frame.getOprandStack().push(frame.getLocalVariableValue(1));
			System.out.println("pushed 1st ref item on to operand.");
			break;
		case "iload_1":
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			frame.getOprandStack().push(frame.getLocalVariableValue(1));
			System.out.println("pushed 1st integer item on to operand.");
			break;
		case "iload_2":
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			frame.getOprandStack().push(frame.getLocalVariableValue(2));
			System.out.println("pushed 2nd integer item on to operand.");
			break;
		case "astore_1":
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			JavaObject jo = frame.getOprandStack().pop();
			System.out.println("set 1st objRef item on to operand." + jo);
			frame.setLocalVariableValue(1, jo);
			break;
		default:
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
			break;
		}
		
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printNoOperandCmd(this);
	}

}
