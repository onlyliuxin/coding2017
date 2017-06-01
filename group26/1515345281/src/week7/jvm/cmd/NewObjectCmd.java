package week7.jvm.cmd;

import week7.jvm.clz.ClassFile;

public class NewObjectCmd extends TwoOperandCmd{

	protected NewObjectCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public String toString(){
		return this.getOperandAsClassInfo();
	}
	
}
