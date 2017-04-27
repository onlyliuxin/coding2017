package org.xukai.jvm.cmd;


import org.xukai.jvm.clz.ClassFile;
import org.xukai.jvm.constant.ConstantPool;

public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	

}
