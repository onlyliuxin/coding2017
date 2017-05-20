package com.coderising.jvm.constant;

public class StringInfo extends ConstantInfo {

	private int tag = ConstantInfo.STRING_INFO;
	private int index;
	
	public StringInfo(ConstantPool constantPool){
		super(constantPool);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getStringName(){
		
		ConstantPool pool = this.getConstantPool();
		Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(getIndex());
		return utf8Info.getValue();
	}

	@Override
	public int getType() {
		return tag;
	}

}
