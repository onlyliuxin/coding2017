package jvm.cmd;

import jvm.clz.ClassFile;
import jvm.constant.ConstantPool;
import jvm.engine.ExecutionResult;
import jvm.engine.Heap;
import jvm.engine.JavaObject;
import jvm.engine.StackFrame;

public class IncrementCmd extends TwoOperandCmd {

	public IncrementCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
		
	}

	@Override
	public String toString() {
		
		return this.getOffset()+":"+this.getOpCode()+ " " +this.getReadableCodeText();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		int index = this.getOprand1();
		
		int constValue = this.getOprand2();
		
		int currentValue = frame.getLocalVariableValue(index).getIntValue();
		
		JavaObject jo = Heap.getInstance().newInt(constValue+currentValue);
		
		frame.setLocalVariableValue(index, jo);
		

	}

	@Override
	public String toString(ConstantPool pool) {
		return super.getOperandAsMethod(pool);
	}

}