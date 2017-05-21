package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand {

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString() {
		return this.getOffset() + ":" + this.getOpCode() + " "
				+ this.getReadableCodeText();
	}

	public int getLength() {
		return 1;
	}

	public void execute(StackFrame frame, ExecutionResult result) {
		String opCode = this.getOpCode();
		if (opCode.equals(ByteCodeCommand.aconst_null)) {
			frame.getOprandStack().push(null);
		} else if (opCode.equals(ByteCodeCommand.dup)) {
			JavaObject javaObject = frame.getOprandStack().peek();
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.aload_0)) {
			JavaObject javaObject = frame.getLocalVariableValue(0);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.aload_1)) {
			JavaObject javaObject = frame.getLocalVariableValue(1);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.aload_2)) {
			JavaObject javaObject = frame.getLocalVariableValue(2);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.iload_1)) {
			JavaObject javaObject = frame.getLocalVariableValue(1);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.iload_2)) {
			JavaObject javaObject = frame.getLocalVariableValue(2);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.iload_3)) {
			JavaObject javaObject = frame.getLocalVariableValue(3);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.fload_3)) {
			JavaObject javaObject = frame.getLocalVariableValue(3);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.voidreturn)) {
			result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
		} else if (opCode.equals(ByteCodeCommand.ireturn)) {
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject javaObject = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.freturn)) {
			StackFrame callerFrame = frame.getCallerFrame();
			JavaObject javaObject = frame.getOprandStack().pop();
			callerFrame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.astore_1)) {
			JavaObject javaObject = frame.getOprandStack().pop();
			frame.setLocalVariableValue(1, javaObject);
		} else if (opCode.equals(ByteCodeCommand.iconst_0)) {
			JavaObject javaObject = Heap.getInstance().newInt(0);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.iconst_1)) {
			JavaObject javaObject = Heap.getInstance().newInt(1);
			frame.getOprandStack().push(javaObject);
		} else if (opCode.equals(ByteCodeCommand.istore_1)) {
			JavaObject javaObject = frame.getOprandStack().pop();
			frame.setLocalVariableValue(1, javaObject);
		} else if (opCode.equals(ByteCodeCommand.istore_2)) {
			JavaObject javaObject = frame.getOprandStack().pop();
			frame.setLocalVariableValue(2, javaObject);
		} else if (opCode.equals(ByteCodeCommand.iadd)) {
			int int1 = frame.getOprandStack().pop().getIntValue();
			int int2 = frame.getOprandStack().pop().getIntValue();
			JavaObject sumJavaObject = Heap.getInstance().newInt(int1 + int2);
			frame.getOprandStack().push(sumJavaObject);
		} else {
			throw new RuntimeException(
					"operation :" + opCode+"has not implemented yet");
		}
	}

	@Override
	public String toString(ConstantPool constantPool) {
		// TODO Auto-generated method stub
		return this.getOffset() + ":" + this.getOpCode() + " "
		+ this.getReadableCodeText();
	}
}
