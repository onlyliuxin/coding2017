package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo {

	private int tag = ConstantInfo.NAME_AND_TYPE_INFO;
	private int Index_Name;
	private int Index_Describe;
	
	public NameAndTypeInfo(ConstantPool constantPool){
		super(constantPool);
	}
	public int getIndex_Name() {
		return Index_Name;
	}

	public void setIndex_Name(int index_Name) {
		Index_Name = index_Name;
	}

	public int getIndex_Describe() {
		return Index_Describe;
	}

	public void setIndex_Describe(int index_Describe) {
		Index_Describe = index_Describe;
	}

	public String getNameInfo(){
	
		ConstantPool pool = this.getConstantPool();
		Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(Index_Name);
		return utf8Info.getValue();
	}
	public String getDescribeInfo(){
		
		ConstantPool pool = this.getConstantPool();
		Utf8Info utf8Info = (Utf8Info) pool.getConstantInfo(Index_Describe);
		return utf8Info.getValue();
	}
	
	@Override
	public int getType() {
		return tag;
	}

}
