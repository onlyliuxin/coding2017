package com.coderising.jvm.constant;

import com.coderising.jvm.constant.ConstantInfo.Visitor;

public class StringInfo extends ConstantInfo{

	private int type = ConstantInfo.STRING_INFO;
	private int index;//  指向字符串字面量的索引
	
	public StringInfo(ConstantPool pool){
		super(pool);
	}
	
	
	@Override
	public int getType() {
		return type;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public String toString(){
		return this.getConstantPool().getUTF8String(index);
	}


	@Override
	public void accept(Visitor visitor) {
		visitor.visitString(this);
		
	}

}
