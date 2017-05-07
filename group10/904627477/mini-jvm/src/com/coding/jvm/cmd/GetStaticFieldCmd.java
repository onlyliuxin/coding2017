package com.coding.jvm.cmd;

import com.coding.jvm.clz.ClassFile;
import com.coding.jvm.constant.ClassInfo;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.constant.FieldRefInfo;
import com.coding.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
