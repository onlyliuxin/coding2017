package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ConstantPool;
import jvm.constant.FieldRefInfo;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;


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
		String fieldName = fieldRef.getFieldName();
		JavaObject jo = frame.getOprandStack().pop();
		JavaObject fieldValue = jo.getFieldValue(fieldName);
		
		frame.getOprandStack().push(fieldValue);
	}

	
	

}
