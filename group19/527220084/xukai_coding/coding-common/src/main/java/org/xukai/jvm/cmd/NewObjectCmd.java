package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ClassInfo;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.Heap;
import org.xukai.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd {
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ClassInfo classInfo = (ClassInfo) getConstantInfo(getIndex());
		Heap.getInstance().newObject(classInfo.getClassName());
	}


}
