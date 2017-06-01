package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class InvokeSpecialCmd extends TwoOperandCmd {

	protected InvokeSpecialCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsMethod();
	}
}
