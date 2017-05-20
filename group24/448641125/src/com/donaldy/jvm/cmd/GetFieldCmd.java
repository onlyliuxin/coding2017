package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.FieldRefInfo;
import com.donaldy.jvm.engine.ExecutionResult;
import com.donaldy.jvm.engine.JavaObject;
import com.donaldy.jvm.engine.StackFrame;


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
