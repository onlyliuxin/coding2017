package com.dudy.learn01.coderising.jvm.cmd;
import com.dudy.learn01.coderising.jvm.clz.ClassFile;
import com.dudy.learn01.coderising.jvm.constant.ConstantInfo;
import com.dudy.learn01.coderising.jvm.constant.ConstantPool;
import com.dudy.learn01.coderising.jvm.constant.StringInfo;

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
	
}
