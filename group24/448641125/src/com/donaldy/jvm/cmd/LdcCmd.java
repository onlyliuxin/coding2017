package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ConstantInfo;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.StringInfo;
import com.donaldy.jvm.engine.ExecutionResult;
import com.donaldy.jvm.engine.StackFrame;

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
