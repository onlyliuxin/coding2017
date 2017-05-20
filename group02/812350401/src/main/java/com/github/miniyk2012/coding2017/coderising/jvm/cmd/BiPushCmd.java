package com.github.miniyk2012.coding2017.coderising.jvm.cmd;


import com.github.miniyk2012.coding2017.coderising.jvm.clz.ClassFile;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;

public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
