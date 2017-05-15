package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ClassInfo;
import jvm.constant.ConstantPool;
import jvm.constant.FieldRefInfo;
import jvm.constant.UTF8Info;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int index = this.getIndex();
		FieldRefInfo info = (FieldRefInfo) this.getConstantInfo(index);
		String className = info.getClassName();
		String fieldName = info.getFieldName();
		String fieldType = info.getFieldType();
		
		if("java/lang/System".equals(className) 
				&& "out".equals(fieldName) 
				&& "Ljava/io/PrintStream;".equals(fieldType)){
			JavaObject jo = Heap.getInstance().newObject(className);
			frame.getOprandStack().push(jo);
		}
		//TODO 处理非System.out的情况
	}

}
