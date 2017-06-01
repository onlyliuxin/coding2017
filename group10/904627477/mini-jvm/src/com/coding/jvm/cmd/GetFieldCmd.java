package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.constant.FieldRefInfo;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.JavaObject;
import com.coding.jvm.engine.StackFrame;


public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());
		JavaObject jo = frame.getOprandStack().pop();
		JavaObject fieldValue = jo.getFieldValue(fieldRef.getFieldName());
		frame.getOprandStack().push(fieldValue);
	}

	
	

}
