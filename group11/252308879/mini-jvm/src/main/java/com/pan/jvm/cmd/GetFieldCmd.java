package com.pan.jvm.cmd;

import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.constant.FieldRefInfo;
import com.pan.jvm.engine.ExecutionResult;
import com.pan.jvm.engine.JavaObject;
import com.pan.jvm.engine.StackFrame;


public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}

	@Override
	public void execute(StackFrame frame,ExecutionResult result) {
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String fieldName = fieldRef.getFieldName();
		JavaObject jo = frame.getOprandStack().pop();
		JavaObject fieldValue = jo.getFieldValue(fieldName);
		
		frame.getOprandStack().push(fieldValue);
		
		
		
	}
	

}
