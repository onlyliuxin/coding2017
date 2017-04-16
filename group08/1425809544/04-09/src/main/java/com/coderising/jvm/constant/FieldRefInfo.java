package com.coderising.jvm.constant;


/**
 * 字段，方法和接口方法由类似的结构表示：
 */
public class FieldRefInfo extends ConstantInfo{
	/**
	 * CONSTANT_Fieldref_info 结构的 tag 项的值为 CONSTANT_Fieldref（9）。
	 */
	private int type = ConstantInfo.FIELD_INFO;
	/**
	 * class_index 项的值必须是对常量池的有效索引，常量池在该索引处的项必须是CONSTANT_Class_info（§4.4.1）结构，表示一个类或接口，当前字段或方法是这个类或接口的成员。
	 */
	private int classInfoIndex;
	/**
	 * name_and_type_index 项的值必须是对常量池的有效索引，常量池在该索引处的项必须是 CONSTANT_NameAndType_info（§4.4.6）结构，它表示当前字段或方法的名字和描述符
	 */
	private int nameAndTypeIndex;
	
	public FieldRefInfo(ConstantPool pool) {
		super(pool);
	}
	public int getType() {
		return type;
	}
	
	public int getClassInfoIndex() {
		return classInfoIndex;
	}
	public void setClassInfoIndex(int classInfoIndex) {
		this.classInfoIndex = classInfoIndex;
	}
	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
	public void setNameAndTypeIndex(int nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public String toString(){
		
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
		
		return getClassName() +" : "+  typeInfo.getName() + ":" + typeInfo.getTypeInfo() +"]";
	}
	
	public String getClassName(){
		
		ClassInfo classInfo = (ClassInfo) this.getConstantInfo(this.getClassInfoIndex());
		
		UTF8Info utf8Info = (UTF8Info)this.getConstantInfo(classInfo.getUtf8Index());
		
		return utf8Info.getValue();
		
	}
	
	public String getFieldName(){
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getName();		
	}
	
	public String getFieldType(){
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getTypeInfo();	
	}
}
