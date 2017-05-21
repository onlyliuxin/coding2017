package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;


public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}
	@Override
	public String toString() {
		ConstantPool pool=clzFile.getConstantPool();
		return toString(pool);
	}
	@Override
	public String toString(ConstantPool pool) {
		
		return getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		FieldRefInfo fieldRef = (FieldRefInfo)getConstantInfo(getIndex());
		String fieldName = fieldRef.getFieldName();
		JavaObject javaObject = frame.getOprandStack().pop();
		JavaObject fieldValue = javaObject.getFieldValue(fieldName);
		frame.getOprandStack().push(fieldValue);

	}

	
	

}
