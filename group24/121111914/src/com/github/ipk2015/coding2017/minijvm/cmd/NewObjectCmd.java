package com.github.ipk2015.coding2017.minijvm.cmd;

import com.github.ipk2015.coding2017.minijvm.clz.ClassFile;
import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.engine.ExecutionResult;
import com.github.ipk2015.coding2017.minijvm.engine.StackFrame;

public class NewObjectCmd extends TwoOperandCmd{
	
	public NewObjectCmd(ClassFile clzFile, String opCode){
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsClassInfo(pool);
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		// TODO Auto-generated method stub
		
	}

	
}
