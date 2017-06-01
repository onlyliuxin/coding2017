package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.print.CommandPrinter;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		ConstantInfo info = pool.getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ConstantInfo info = this.getConstantInfo(getOperand());
		result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
		System.out.println("loading constant: " + info  + " operand num: " + getOperand());
		switch(info.getType()){
		case ConstantInfo.STRING_INFO:
			String val = ((StringInfo)info).toString();
			JavaObject obj = Heap.getInstance().newString(val);
			frame.getOprandStack().push(obj);
			break;
		default:
			throw new RuntimeException("ldc cmd executing unimplemented constant info type.");
		}
		
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printLdcCmd(this);
	}
	
}
