package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo{
	public  int type = ConstantInfo.NAME_AND_TYPE_INFO;
	


	private int index_Name;
	private int index_Describe;
	
	public int getIndex_Name() {
		return index_Name;
	}


	public void setIndex_Name(int index_Name) {
		this.index_Name = index_Name;
	}


	public int getIndex_Describe() {
		return index_Describe;
	}


	public void setIndex_Describe(int index_Describe) {
		this.index_Describe = index_Describe;
	}
	
	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
	}
	

	public int getType() {
		return type;
	}
	
	
	public String getName(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info1 = (UTF8Info)pool.getConstantInfo(index_Name);
		return utf8Info1.getValue();
	}
	
	public String getTypeInfo(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info2 = (UTF8Info)pool.getConstantInfo(index_Describe);
		return utf8Info2.getValue();
	}
	
	public String toString(){
		return "(" + getName() + "," + getTypeInfo()+")";
	}
}
