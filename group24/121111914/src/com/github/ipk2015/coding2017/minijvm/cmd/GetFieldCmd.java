package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.constant.FieldRefInfo;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.JavaObject;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;

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
		
		FieldRefInfo fieldRefInfo = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String fieldName = fieldRefInfo.getFieldName();
		JavaObject javaObject = frame.getOprandStack().pop();
		JavaObject fieldValue = javaObject.getFieldValue(fieldName);
		frame.getOprandStack().push(fieldValue);
	}

	
	

}
