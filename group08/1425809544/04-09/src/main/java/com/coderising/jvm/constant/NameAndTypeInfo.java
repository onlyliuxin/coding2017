package com.coderising.jvm.constant;

/**
 * CONSTANT_NameAndType_info 结构用于表示字段或方法，但是和 4.4.2 章节中介绍的 3个结构不同，CONSTANT_NameAndType_info 结构没有标识出它所属的类或接口，
 */
public class NameAndTypeInfo extends ConstantInfo{
	/**
	 *CONSTANT_NameAndType_info 结构的 tag 项的值为 CONSTANT_NameAndType(12)
	 */
	public  int type = ConstantInfo.NAME_AND_TYPE_INFO;
	/**
	 *name_index 项的值必须是对常量池的有效索引，常量池在该索引处的项必须是CONSTANT_Utf8_info（§4.4.7）结构，这个结构要么表示特殊的方法名<init>（§2.9），要么表示一个有效的字段或方法的非限定名（Unqualified Name）。
	 */
	private int index1;
	/**
	 *descriptor_index 项的值必须是对常量池的有效索引，常量池在该索引处的项必须是CONSTANT_Utf8_info（§4.4.7）结构，这个结构表示一个有效的字段描述符（§4.3.2）或方法描述符（§4.3.3）
	 */
	private int index2;
	
	public NameAndTypeInfo(ConstantPool pool) {
		super(pool);
	}
	
	public int getIndex1() {
		return index1;
	}
	public void setIndex1(int index1) {
		this.index1 = index1;
	}
	public int getIndex2() {
		return index2;
	}
	public void setIndex2(int index2) {
		this.index2 = index2;
	}
	public int getType() {
		return type;
	}
	
	
	public String getName(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info1 = (UTF8Info)pool.getConstantInfo(index1);
		return utf8Info1.getValue();
	}
	
	public String getTypeInfo(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info2 = (UTF8Info)pool.getConstantInfo(index2);
		return utf8Info2.getValue();
	}
	
	public String toString(){
		return "(" + getName() + "," + getTypeInfo()+")";
	}
}
