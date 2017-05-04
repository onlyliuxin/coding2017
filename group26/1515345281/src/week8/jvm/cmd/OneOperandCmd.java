package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.StackFrame;

public abstract class OneOperandCmd extends ByteCodeCommand{

	int operand;
	
	protected OneOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	public int getLength(){
		return 2;
	}
	
	public int getOperand() {
		return operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
	}
}
