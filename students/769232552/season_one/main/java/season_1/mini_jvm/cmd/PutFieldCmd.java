package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.ClassInfo;
import mini_jvm.constant.FieldRefInfo;
import mini_jvm.constant.NameAndTypeInfo;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.JavaObject;
import mini_jvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
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
