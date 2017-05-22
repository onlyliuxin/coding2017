package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}
	@Override
	public String toString() {
		ConstantPool pool=clzFile.getConstantPool();
		return toString(pool);
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		FieldRefInfo fieldRefInfo=(FieldRefInfo)getConstantInfo(getIndex());
		ClassInfo classInfo=(ClassInfo)getConstantInfo(fieldRefInfo.getClassInfoIndex());
		NameAndTypeInfo nameAndTypeInfo=(NameAndTypeInfo)getConstantInfo(fieldRefInfo.getNameAndTypeIndex());
		String fieldName=nameAndTypeInfo.getName();
		String fieldType=nameAndTypeInfo.getTypeInfo();
		JavaObject field=frame.getOprandStack().pop();
		JavaObject object=frame.getOprandStack().pop();
		object.setFieldValue(fieldName, field);
//		frame.getOprandStack().push(object);
	}


}
