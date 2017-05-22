package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}
	@Override
	public String toString() {
		ConstantPool pool=clzFile.getConstantPool();
		return toString(pool);
	}
	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		ClassInfo classInfo=(ClassInfo)getConstantInfo(getIndex());
		String className=classInfo.getClassName();
		JavaObject javaObject=Heap.getInstance().newObject(className);
		frame.getOprandStack().push(javaObject);
		
	}

	
}
