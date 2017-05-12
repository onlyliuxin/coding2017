package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;
import com.coderising.jvm.print.CommandPrinter;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRef = (FieldRefInfo) getConstantInfo(getIndex());
		System.out.println("Class Name: "+fieldRef.getClassName()); //used for checking with objref
		System.out.println("Field Name: " + fieldRef.getFieldName());
		System.out.println("Field Type: " + fieldRef.getFieldType()); //used to check with the field value
		
		JavaObject value = frame.getOprandStack().pop();
		JavaObject objRef = frame.getOprandStack().pop();
		
		objRef.setFieldValue(fieldRef.getFieldName(), value);
		
		System.out.println(frame);
		result.setNextAction(ExecutionResult.RUN_NEXT_CMD);
	}

	@Override
	public void print(CommandPrinter printer) {
		printer.printPutFieldCmd(this);
	}


}
