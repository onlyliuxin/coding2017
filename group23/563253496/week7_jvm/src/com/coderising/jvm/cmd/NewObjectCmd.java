package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	
}
