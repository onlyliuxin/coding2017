package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;
import week7.jvm.constant.ConstantInfo;
import week7.jvm.constant.StringInfo;

public class LdcCmd extends OneOperandCmd{

	protected LdcCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		
		ConstantInfo info=this.getConstantInfo(getOperand());
	
		String value="TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo=(StringInfo)info;
			value=strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" "+this.getReadableCodeText()+"  "+value;
	}
}
