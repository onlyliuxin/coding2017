package com.coding.mini_jvm.src.com.coderising.jvm.cmd;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.FieldRefInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.NameAndTypeInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.ExecutionResult;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.JavaObject;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.StackFrame;

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
		FieldRefInfo fieldRefInfo = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRefInfo.getNameAndTypeIndex());
		String fieldName = nameAndTypeInfo.getName();
		JavaObject fieldValue = frame.getOprandStack().pop();
		JavaObject objectRef = frame.getOprandStack().pop();
		objectRef.setFieldValue(fieldName, fieldValue);
	}


}
