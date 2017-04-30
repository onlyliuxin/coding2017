package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.FieldRefInfo;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.JavaObject;
import org.xukai.jvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(clzFile.getConstantPool());
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String fieldName = fieldRef.getFieldName();
		JavaObject jo = frame.getOprandStack().pop();
		JavaObject fieldValue = jo.getFieldValue(fieldName);
		
		frame.getOprandStack().push(fieldValue);
		
		
		
	}
	

}
