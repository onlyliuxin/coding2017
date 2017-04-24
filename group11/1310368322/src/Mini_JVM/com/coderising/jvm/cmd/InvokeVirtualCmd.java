package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;

public class InvokeVirtualCmd extends TwoOperandCmd{

	protected InvokeVirtualCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}
	
	@Override
	public String toString(ConstantPool pool){
		return super.getOperandAsMethod(pool);
	}

}
