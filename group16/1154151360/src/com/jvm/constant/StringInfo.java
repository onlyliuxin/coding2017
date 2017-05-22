package com.jvm.constant;

public class StringInfo extends ConstantInfo{
	private int tag = ConstantInfo.STRING_INFO;
	
	private int UTF8_index;

	public int getTag() {
		return tag;
	}
	
	public void setTag(int tag) {
		this.tag = tag;
	}
	
	public int getUTF8_index() {
		return UTF8_index;
	}
	
	public void setUTF8_index(int uTF8_index) {
		UTF8_index = uTF8_index;
	}

	@Override
	public String toString() {
		return "StringInfo [tag= "+tag+", UTF8_index= "+UTF8_index+"]";
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return getTag();
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visitString(this);
		
	}
  
	
}
