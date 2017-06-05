package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.StackFrame;

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

	public static OneOperandCmd getOneOperandCmd(ClassFile clzFile,String opCode){
		OneOperandCmd cmd = new OneOperandCmd(clzFile, opCode) {
			
			@Override
			public String toString(ConstantPool pool) {
				
				return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
			}

			@Override
			public void execute(StackFrame frame, ExecutionResult result) {
				// TODO Auto-generated method stub
				
			}
		};
		return cmd;
	}
}
