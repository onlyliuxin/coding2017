package com.github.ipk2015.coding2017.minijvm.cmd;



import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;

public abstract class OneOperandCmd extends ByteCodeCommand {

	private int operand;
	
	public OneOperandCmd(ClassFile clzFile,String opCode) {
		super(clzFile, opCode);
		
	}
	public  int getOperand() {
		
		return this.operand;
	}

	public void setOperand(int oprand1) {
		this.operand = oprand1;
		
	}
	public  int getLength(){
		return 2;
	}

	
}
