package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.FieldRefInfo;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.JavaObject;
import mini_jvm.engine.StackFrame;

public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString() {
		return super.getOperandAsField();
	}

	//获取一个对象的字段
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		//根据常量池索引获取字段信息
		FieldRefInfo fieldRef = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String fieldName = fieldRef.getFieldName();
		JavaObject jo = frame.getOprandStack().pop();

		//根据对象的字段名获取value
		JavaObject fieldValue = jo.getFieldValue(fieldName);

		frame.getOprandStack().push(fieldValue);
		
	}

	
	

}
