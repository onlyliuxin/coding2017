package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class NullConstantInfo extends ConstantInfo{

	public NullConstantInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}

	public NullConstantInfo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getType() {
		return -1;
	}

	@Override
	public void accept(Visitor visitor) {
		
		
	}
}
