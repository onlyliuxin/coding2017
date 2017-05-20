package com.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo{

	private int tag = ConstantInfo.NAME_AND_TYPE_INFO;
	
	private int UTF8Name_index;
	
	private int UTF8Describe_index;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getUTF8Name_index() {
		return UTF8Name_index;
	}

	public void setUTF8Name_index(int uTF8Name_index) {
		UTF8Name_index = uTF8Name_index;
	}

	public int getUTF8Describe_index() {
		return UTF8Describe_index;
	}

	public void setUTF8Describe_index(int uTF8Describe_index) {
		UTF8Describe_index = uTF8Describe_index;
	}

	@Override
	public String toString() {
		return "NameAndTypeInfo [tag=" + tag + ", UTF8Name_index="
				+ UTF8Name_index + ", UTF8Describe_index=" + UTF8Describe_index
				+ "]";
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return getTag();
	}

	@Override
	public void accept(Vistor vistor) {
		vistor.visitNameAndType(this);
		
	}
	
	
}
