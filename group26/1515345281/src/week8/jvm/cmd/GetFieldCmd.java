package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.constant.FieldRefInfo;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.StackFrame;


public class GetFieldCmd extends TwoOperandCmd {

	protected GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsField();
	}

	@Override
	public void execute(StackFrame stackFrame, ExecutorResult result) {
		
		FieldRefInfo fieldRef=(FieldRefInfo) this.getConstantInfo(getIndex());
		String fieldName=fieldRef.getFieldName();
				
		JavaObject jo=stackFrame.getOperandStack().pop();
		JavaObject fieldValue=jo.getFieldValues(fieldName);
		
		stackFrame.getOperandStack().push(fieldValue);
		
	}
}
