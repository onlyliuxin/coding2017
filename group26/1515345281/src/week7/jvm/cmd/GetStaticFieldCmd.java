package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class GetStaticFieldCmd extends TwoOperandCmd {

	protected GetStaticFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsField();
	}
}