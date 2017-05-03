package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantPool;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.Heap;
import miniJVM.engine.JavaObject;
import miniJVM.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	public void execute(StackFrame frame, ExecutionResult result){
		int value = this.getOperand();
		JavaObject jo = Heap.getInstance().newInt(value);
		frame.getOperandStack().push(jo);
	}

}
