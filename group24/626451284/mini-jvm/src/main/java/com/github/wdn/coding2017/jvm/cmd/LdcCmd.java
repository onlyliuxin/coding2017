package com.github.wdn.coding2017.jvm.cmd;


import com.github.wdn.coding2017.jvm.clz.ClassFile;
import com.github.wdn.coding2017.jvm.constant.ConstantInfo;
import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.constant.StringInfo;

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
	
}
