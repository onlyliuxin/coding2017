package com.coderising.jvm.constant;

import com.coderising.jvm.print.PrintVisitor;

public class NullConstantInfo extends ConstantInfo {

	public NullConstantInfo() {

	}

	@Override
	public int getType() {
		return -1;
	}

	@Override
	public void accept(PrintVisitor visitor) {
		visitor.visit(this);
		
	}
	
	
	

}
