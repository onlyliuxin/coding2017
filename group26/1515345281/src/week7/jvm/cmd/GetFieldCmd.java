package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;


public class GetFieldCmd extends TwoOperandCmd {

	protected GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsField();
	}
}
