package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.FieldRefInfo;
import miniJVM.constant.NameAndTypeInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.JavaObject;
import miniJVM.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());

		NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) fieldRef.getConstantInfo(fieldRef.getNameAndTypeIndex());

		String fieldName = nameAndTypeInfo.getName();

		JavaObject fieldValue = frame.getOperandStack().pop();
		JavaObject objectRef = frame.getOperandStack().pop();

		objectRef.setFieldValue(fieldName, fieldValue);
	}
}
