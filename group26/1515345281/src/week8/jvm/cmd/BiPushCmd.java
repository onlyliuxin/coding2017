package week8.jvm.cmd;

import week8.jvm.clz.ClassFile;
import week8.jvm.engine.ExecutorResult;
import week8.jvm.engine.Heap;
import week8.jvm.engine.JavaObject;
import week8.jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd{

	protected BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	} 
	
	@Override
	public String toString(){
		return getOffset()+":"+getOpCode()+" "+getReadableCodeText()+" "+getOperand();
	}

	@Override
	public void execute(StackFrame stackFrame, ExecutorResult result) {
		int value=this.getOperand();
		JavaObject jo=Heap.getInstance().newInt(value);
		stackFrame.getOperandStack().push(jo);
	}

}
