package com.donaldy.jvm.cmd;

import com.donaldy.jvm.clz.ClassFile;
import com.donaldy.jvm.constant.ClassInfo;
import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.FieldRefInfo;
import com.donaldy.jvm.constant.UTF8Info;


public class GetStaticFieldCmd extends TwoOperandCmd {

	public GetStaticFieldCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

}
