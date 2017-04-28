package com.github.HarryHook.coding2017.jvm.cmd;

import com.github.HarryHook.coding2017.jvm.clz.ClassFile;
import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.constant.MethodRefInfo;


public class InvokeSpecialCmd extends TwoOperandCmd {

	public InvokeSpecialCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	

}