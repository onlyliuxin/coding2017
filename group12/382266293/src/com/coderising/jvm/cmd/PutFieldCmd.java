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
	public void execute(StackFrame frame,ExecutionResult result) {	
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		
		ClassInfo clzInfo = (ClassInfo)this.getConstantInfo(fieldRef.getClassInfoIndex());
		NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRef.getNameAndTypeIndex());
		// for example : name
		String fieldName = nameTypeInfo.getName();
		// for example : Ljava/lang/String : 注意：我们不再检查类型
		String fieldType = nameTypeInfo.getTypeInfo();
		
		JavaObject fieldValue = frame.getOprandStack().pop();
		JavaObject objectRef = frame.getOprandStack().pop();
		
		objectRef.setFieldValue(fieldName, fieldValue);
		
	}

}
