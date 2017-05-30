package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ClassInfo;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.engine.ExecutionResult;
import com.donaldy.jvm.engine.Heap;
import com.donaldy.jvm.engine.JavaObject;
import com.donaldy.jvm.engine.StackFrame;

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
