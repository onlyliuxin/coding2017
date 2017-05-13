package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.ConstantPool;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.StackFrame;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		
	}
	
	

}
