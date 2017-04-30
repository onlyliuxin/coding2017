package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.FieldRefInfo;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.JavaObject;
import org.xukai.jvm.engine.StackFrame;

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

		int index = getIndex();
		FieldRefInfo info = (FieldRefInfo)this.getConstantInfo(index);
		String fieldName = info.getFieldName();
		// for example : Ljava/lang/String : 注意：我们不再检查类型
		String fieldType = info.getFieldType();
		JavaObject fieldValue = frame.getOprandStack().pop();
		JavaObject objectRef = frame.getOprandStack().pop();

		objectRef.setFieldValue(fieldName, fieldValue);
	}


}
