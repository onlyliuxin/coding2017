package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.FieldRefInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.JavaObject;
import miniJVM.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRefInfo = (FieldRefInfo) this.getConstantInfo(this.getIndex());
		String fieldName = fieldRefInfo.getFieldName();
		JavaObject jo = frame.getOperandStack().pop();
		JavaObject value = jo.getFieldValue(fieldName);

		frame.getOperandStack().push(value);
	}
	

}
