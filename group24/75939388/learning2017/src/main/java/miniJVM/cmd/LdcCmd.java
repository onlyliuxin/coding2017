package miniJVM.cmd;


import miniJVM.clz.ClassFile;
import miniJVM.constant.ConstantInfo;
import miniJVM.constant.ConstantPool;
import miniJVM.constant.StringInfo;
import miniJVM.engine.ExecutionResult;
import miniJVM.engine.Heap;
import miniJVM.engine.StackFrame;

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
		ConstantInfo constantInfo = this.getConstantPool().getConstantInfo(this.getOperand());

		if(constantInfo instanceof StringInfo){
			String value = constantInfo.toString();
			frame.getOperandStack().push(Heap.getInstance().newString(value));
		}else{
			throw new RuntimeException("process string info only");
		}
	}
}
