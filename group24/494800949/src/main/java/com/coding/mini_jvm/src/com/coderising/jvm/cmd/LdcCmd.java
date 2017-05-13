package com.coding.mini_jvm.src.com.coderising.jvm.cmd;


import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.StringInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.ExecutionResult;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.JavaObject;
import com.coding.mini_jvm.src.com.coderising.jvm.engine.StackFrame;

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
		ClassFile clzFile = this.getClassFile();
		ConstantInfo info = (ConstantInfo)clzFile.getConstantPool().getConstantInfo(this.getOperand());
		String value = "TBD";
		if (info instanceof StringInfo) {
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		JavaObject jo = new JavaObject(JavaObject.STRING);
		jo.setStringValue(value);
		frame.getOprandStack().push(jo);
	}

}
