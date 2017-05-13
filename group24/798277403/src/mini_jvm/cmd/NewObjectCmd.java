package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.ConstantPool;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		
	}

	
}
