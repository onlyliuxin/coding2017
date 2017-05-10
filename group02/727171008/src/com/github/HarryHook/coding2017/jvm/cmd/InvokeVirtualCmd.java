package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;

public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	
	

}