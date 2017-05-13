package mini_jvm.cmd;


import mini_jvm.clz.ClassFile;
import mini_jvm.constant.ConstantInfo;
import mini_jvm.constant.ConstantPool;
import mini_jvm.constant.StringInfo;
import mini_jvm.engine.ExecutionResult;
import mini_jvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		ConstantInfo info = (ConstantInfo)pool.getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		
		
	}
	
}
