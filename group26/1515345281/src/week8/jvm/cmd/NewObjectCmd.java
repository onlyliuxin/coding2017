package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.constant.ClassInfo;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.Heap;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{

	protected NewObjectCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return this.getOperandAsClassInfo();
	}

	@Override
	public void execute(StackFrame stackFrame, ExecutorResult result) {
		
		int index=this.getIndex();
		
		ClassInfo clzInfo=(ClassInfo) this.getConstantInfo(index);
		
		String className=clzInfo.getClassName();
		
		JavaObject jo=Heap.getInstance().newObject(className);
		
		stackFrame.getOperandStack().push(jo);
	}
	
}
