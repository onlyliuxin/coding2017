package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class OneOperandCmd extends ByteCodeCommand{

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
