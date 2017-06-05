package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ClassInfo;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.constant.FieldRefInfo;
import com.coding.jvm.constant.UTF8Info;
import com.coding.jvm.engine.ExecutionResult;
import com.coding.jvm.engine.Heap;
import com.coding.jvm.engine.JavaObject;
import com.coding.jvm.engine.StackFrame;


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
		FieldRefInfo info = (FieldRefInfo)this.getConstantInfo(this.getIndex());
		String className = info.getClassName();
		String fieldName = info.getFieldName();
		String fieldType = info.getFieldType();
		
		if("java/lang/System".equals(className) 
				&& "out".equals(fieldName) 
				&& "Ljava/io/PrintStream;".equals(fieldType)){
			JavaObject jo = Heap.getInstance().newObject(className);
			frame.getOprandStack().push(jo);
		}
		
		
	}

}
