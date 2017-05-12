package week08.jvm.cmd;

import week08.jvm.clz.ClassFile;
import week08.jvm.constant.ConstantInfo;
import week08.jvm.constant.ConstantPool;
import week08.jvm.constant.StringInfo;
import week08.jvm.engine.ExecutionResult;
import week08.jvm.engine.Heap;
import week08.jvm.engine.JavaObject;
import week08.jvm.engine.StackFrame;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile,String opCode) {
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
