package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.print.CommandPrinter;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	//assertOpCodeEquals("0: new #1", cmds[0]);
	@Override
	public String toString(){
		return this.getOffset() + ": " + this.getReadableCodeText() + " #" +  this.getIndex();
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ClassInfo clzInfo = (ClassInfo)this.getConstantInfo(this.getIndex());
		
		//String readableOperand = this.getConstantPool().getUTF8String(this.getIndex());
		System.out.println("Creating new object ref: " + clzInfo.getClassName());
		JavaObject obj = Heap.getInstance().newObject(clzInfo.getClassName());
		
		frame.getOprandStack().push(obj);
		result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
		
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printNewObjectCmd(this);
	}

	
}
