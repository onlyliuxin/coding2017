package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.ConstantPool;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.StackFrame;

public class NoOperandCmd extends ByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

	
	
	public  int getLength(){
		return 1;
	}


	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		
	}

}
