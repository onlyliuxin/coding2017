package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;

public class PutFieldCmd extends TwoOperandCmd {

	public PutFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}


}