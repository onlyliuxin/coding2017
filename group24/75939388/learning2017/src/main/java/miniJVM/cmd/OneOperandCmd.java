package miniJVM.cmd;

import miniJVM.clz.ClassFile;

public abstract class OneOperandCmd extends ByteCodeCommand {

	private int operand;
	
	public OneOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
		
	}
	public  int getOperand() {
		
		return this.operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
		
	}
	public  int getLength(){
		return 2;
	}

}
