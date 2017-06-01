package com.github.miniyk2012.coding2017.coderising.jvm.constant;

import com.github.miniyk2012.coding2017.coderising.jvm.print.ConstantPoolPrinter;

public class NullConstantInfo extends ConstantInfo {

	public NullConstantInfo(){
		
	}
	@Override
	public int getType() {		
		return -1;
	}

	@Override
	public void accept(ConstantPoolPrinter.Visitor visitor) {
		visitor.visitNullConstantInfo(this);
	}
}
