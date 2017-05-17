package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.FieldRefInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.Heap;
import miniJVM.engine.JavaObject;
import miniJVM.engine.StackFrame;

public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		FieldRefInfo fieldRefInfo = (FieldRefInfo) this.getConstantInfo(this.getIndex());
		String className = fieldRefInfo.getClassName();
		String fieldName = fieldRefInfo.getFieldName();
		String fieldType = fieldRefInfo.getFieldType();

		if("java/lang/System".equals(className)
				&& "out".equals(fieldName)
				&& "Ljava/io/PrintStream;".equals(fieldType)){
			JavaObject jo = Heap.getInstance().newObject(className);
			frame.getOperandStack().push(jo);
		}else return;
	}
}
