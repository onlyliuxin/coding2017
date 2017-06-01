package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ConstantPool;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		int value = this.getOperand();
		//在堆Heap中创建对象
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOprandStack().push(jo);
		
	}
	
	

}
