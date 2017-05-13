package com.pan.jvm.cmd;

import com.pan.jvm.clz.ClassFile;
import com.pan.jvm.constant.ClassInfo;
import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.constant.FieldRefInfo;
import com.pan.jvm.constant.UTF8Info;
import com.pan.jvm.engine.ExecutionResult;
import com.pan.jvm.engine.Heap;
import com.pan.jvm.engine.JavaObject;
import com.pan.jvm.engine.StackFrame;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString() {
		
		return super.getOperandAsField();
	}
	
	@Override	
	public void execute(StackFrame frame,ExecutionResult result) {
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
		//TODO 处理非System.out的情况
	}
}
