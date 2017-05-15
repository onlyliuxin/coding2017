package minijvm.cmd;

import minijvm.clz.ClassFile;
import minijvm.engine.ExecutionResult;
import minijvm.engine.Heap;
import minijvm.engine.JavaObject;
import minijvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString() {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

    @Override
    public void execute(StackFrame frame, ExecutionResult result) {
        int value = this.getOperand();
        JavaObject jo = Heap.getInstance().newInt(value);
        frame.getOprandStack().push(jo);
    }
	
	

}
