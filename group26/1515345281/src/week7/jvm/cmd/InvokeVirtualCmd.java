package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class InvokeVirtualCmd extends TwoOperandCmd{

	protected InvokeVirtualCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return super.getOperandAsMethod();
	}
}
