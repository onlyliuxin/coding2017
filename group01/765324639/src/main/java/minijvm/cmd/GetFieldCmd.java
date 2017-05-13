package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.constant.FieldRefInfo;
import minijvm.engine.ExecutionResult;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        FieldRefInfo fieldRefInfo = (FieldRefInfo) this.getConstantInfo(this.getIndex());
        String fieldName = fieldRefInfo.getFieldName();
        JavaObject jo = frame.getOprandStack().pop();
        JavaObject fieldValue = jo.getFieldValue(fieldName);
        frame.getOprandStack().push(fieldValue);
    }

	
	

}
