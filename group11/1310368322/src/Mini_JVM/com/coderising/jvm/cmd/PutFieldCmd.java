package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd{

	protected PutFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}
	
	public void execute(StackFrame frame, ExecutionResult result){
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		
		NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo)this.getConstantInfo(fieldRef.getNameAndTypeIndex());
		// example : name
		String fieldName = nameTypeInfo.getName();
		// example : Ljava/lang/String 
		String fieldType = nameTypeInfo.getTypeInfo();
		
		
	}

}
