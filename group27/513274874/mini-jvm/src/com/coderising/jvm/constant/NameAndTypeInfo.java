package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo{
	public  int type = ConstantInfo.NAME_AND_TYPE_INFO;
	
	private int name_index;
	private int descriptor_index;
	
	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
	}

	public int getNameIndex() {
		return name_index;
	}

	public void setNameIndex(int name_index) {
		this.name_index = name_index;
	}

	public int getDescriptorIndex() {
		return descriptor_index;
	}

	public void setDescriptorIndex(int descriptor_index) {
		this.descriptor_index = descriptor_index;
	}

	public int getType() {
		return type;
	}
	
	
	public String getName(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info1 = (UTF8Info)pool.getConstantInfo(name_index);
		return utf8Info1.getValue();
	}
	
	public String getTypeInfo(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info2 = (UTF8Info)pool.getConstantInfo(descriptor_index);
		return utf8Info2.getValue();
	}
	
	public String toString(){
		return "(" + getName() + "," + getTypeInfo()+")";
	}
}
