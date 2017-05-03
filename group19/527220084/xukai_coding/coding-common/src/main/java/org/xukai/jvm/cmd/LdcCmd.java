package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantInfo;
import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.StringInfo;
import org.xukai.jvm.engine.ExecutionResult;
import org.xukai.jvm.engine.Heap;
import org.xukai.jvm.engine.JavaObject;
import org.xukai.jvm.engine.StackFrame;

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
		ConstantInfo constantInfo = getConstantPool().getConstantInfo(this.getOperand());
		if(constantInfo instanceof StringInfo){
			StringInfo strInfo = (StringInfo)constantInfo;
			String value = strInfo.toString();
			JavaObject jo = Heap.getInstance().newString(value);
			frame.getOprandStack().push(jo);
		}
		else{
			//TBD 处理其他类型
			throw new RuntimeException("Only support StringInfo constant");
		}

	}

}
