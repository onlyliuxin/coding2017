package com.jvm.constant;

public class ClassInfo  extends ConstantInfo{
	private int tag = ConstantInfo.CLASS_INFo;
	
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
		return "CLassInfo [tag= "+tag+", UTF8_index="+UTF8_index+"]";
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visitClassInfo(this);
		
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return getTag();
	}

	
}
