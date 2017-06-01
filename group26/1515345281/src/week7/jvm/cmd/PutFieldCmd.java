package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class PutFieldCmd extends TwoOperandCmd {

	protected PutFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsField();
	}
}