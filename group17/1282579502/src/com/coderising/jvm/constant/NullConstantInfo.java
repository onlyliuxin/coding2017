package com.coderising.jvm.constant;

import com.coderising.jvm.print.ConstantPoolPrinterInterface;

public class NullConstantInfo extends ConstantInfo{

	public NullConstantInfo(){
		
	}
	@Override
	public int getType() {		
		return -1;
	}
	@Override
	public void print(ConstantPoolPrinterInterface cpp) {
		cpp.printNullConstInfo(this);
	}
	
	
	
}
