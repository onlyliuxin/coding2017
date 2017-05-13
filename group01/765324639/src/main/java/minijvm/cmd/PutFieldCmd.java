package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.ClassInfo;
import minijvm.constant.FieldRefInfo;
import minijvm.constant.NameAndTypeInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        FieldRefInfo fieldRefInfo = (FieldRefInfo) this.getConstantInfo(this.getIndex());
        ClassInfo classInfo = (ClassInfo) this.getConstantInfo(fieldRefInfo.getClassInfoIndex());
        NameAndTypeInfo nameAndTypeInfo = (NameAndTypeInfo) this.getConstantInfo(fieldRefInfo.getNameAndTypeIndex());
        String fieldName = nameAndTypeInfo.getName();
        String fieldTye = nameAndTypeInfo.getTypeInfo();
        
        JavaObject fieldValue = frame.getOprandStack().pop();
        JavaObject objectRef = frame.getOprandStack().pop();
        objectRef.setFieldValue(fieldName, fieldValue);
    }


}
