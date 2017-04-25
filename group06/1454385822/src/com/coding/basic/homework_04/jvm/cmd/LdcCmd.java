package com.coding.basic.homework_04.jvm.cmd;

import com.coding.basic.homework_04.jvm.clz.ClassFile;
import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.info.StringInfo;

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

//	@Override
//	public void execute(StackFrame frame, ExecutionResult result) {
//		
//		
//	}
	
}
