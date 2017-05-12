package com.coderising.jvm.constant;

import com.coderising.jvm.print.ConstantPoolPrinterInterface;

public class FloatRefInfo extends ConstantInfo{

	public FloatRefInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	
	public int getType(){
		return ConstantInfo.FLOAT_INFO;
	}
	
	public float getFloat(){
		return Float.NaN;
	}
	
	public void print(ConstantPoolPrinterInterface cpp){
		cpp.printFloatInfo(this);
	}

}
