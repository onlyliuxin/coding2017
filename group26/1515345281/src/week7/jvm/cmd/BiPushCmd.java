package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class BiPushCmd extends OneOperandCmd{

	protected BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	} 
	
	@Override
	public String toString(){
		return getOffset()+":"+getOpCode()+" "+getReadableCodeText()+"  "+getOperand();
	}

}
