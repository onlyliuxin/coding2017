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
	public String toString() {
		
		return super.getOperandAsClassInfo();
	}
	public void execute(StackFrame frame,ExecutionResult result){
		
		int index = this.getIndex();
		
		ClassInfo info = (ClassInfo)this.getConstantInfo(index);
		
		String clzName = info.getClassName();
		
		//在Java堆上创建一个实例
		JavaObject jo = Heap.getInstance().newObject(clzName);
		
		frame.getOprandStack().push(jo);
		
		
		
	}
	
}
