package com.jvm.constant;

public class UTF8Info extends ConstantInfo{

	private int tag = ConstantInfo.UTF8_INFO;
	
	private int length ;
	
	private String value;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "UTF8Info [tag= "+tag+", length="+length+", value= "+value+" ]";
	}
	
	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return getTag();
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visistUTF8(this);
		
	}
	
	
}
