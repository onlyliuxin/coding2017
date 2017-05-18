package com.pan.jvm.cmd;

import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.constant.ConstantInfo;
import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.engine.ExecutionResult;
import com.pan.jvm.engine.Heap;
import com.pan.jvm.engine.JavaObject;
import com.pan.jvm.engine.StackFrame;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
	
		return this.getOffset()+":"+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	public void execute(StackFrame frame,ExecutionResult result){
		int value = this.getOperand();
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOprandStack().push(jo);
		
	}
	

}
