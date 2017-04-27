package com.github.orajavac.coding2017.jvm.cmd;

import com.github.orajavac.coding2017.jvm.clz.ClassFile;
import com.github.orajavac.coding2017.jvm.constant.ClassInfo;
import com.github.orajavac.coding2017.jvm.constant.ConstantPool;
import com.github.orajavac.coding2017.jvm.constant.FieldRefInfo;
import com.github.orajavac.coding2017.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
