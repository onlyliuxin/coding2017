package com.coderising.jvm.constant;

import com.coderising.jvm.print.ConstantPoolPrinterInterface;

public class IntegerInfo extends ConstantInfo{

	int val = 0;
	public IntegerInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	
	public int getType(){
		return ConstantInfo.INTEGER_INFO;
	}
	
	public void setInteger(int val){
		this.val = val;
	}
	
	public int getInteger(){
		return val;
	}

	@Override
	public void print(ConstantPoolPrinterInterface cpp) {
		cpp.printIntegerInfo(this);
		
	}
}
