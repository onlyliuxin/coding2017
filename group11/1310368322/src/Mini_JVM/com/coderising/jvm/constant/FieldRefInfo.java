package com.coderising.jvm.constant;

public class FieldRefInfo extends ConstantInfo{
	
	private int type = ConstantInfo.FIELD_INFO;
	private int classInfoIndex;//  指向 类信息的索引
	private int nameAndTypeIndex;// 指向该字段 的 名字和类型 的索引
	
	public FieldRefInfo(ConstantPool pool){
		super(pool);
	}

	public int getType() {
		return type;
	}
	
	public int getClassInfoIndex(){
		return classInfoIndex;
	}
	
	public void setClassInfoIndex(int classInfoIndex){
		this.classInfoIndex = classInfoIndex;
	}
	
	public int getNameAndTypeIndex(){
		return nameAndTypeIndex;
	}
	
	public void setNameAndTypeIndex(int nameAndTypeIndex){
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public String toString(){
		NameAndTypeInfo typeInfo = (NameAndTypeInfo)this.getConstantInfo(this.getNameAndTypeIndex());
		return getClassName() + " : " + typeInfo.getName() + typeInfo.getTypeInfo() + "]";
	}
	
	public String getClassName(){
		ClassInfo classInfo = (ClassInfo)this.getConstantInfo(this.getClassInfoIndex());
		UTF8Info utf8Info = (UTF8Info)this.getConstantInfo(classInfo.getUtf8Index());
		return utf8Info.getValue();
	}
	
	public String getFieldName(){
		NameAndTypeInfo typeInfo = (NameAndTypeInfo)this.getConstantInfo(nameAndTypeIndex);
		return typeInfo.getName();
	}
	
	public String getFieldType(){
		NameAndTypeInfo typeInfo = (NameAndTypeInfo)this.getConstantInfo(nameAndTypeIndex);
		return typeInfo.getTypeInfo();
	}
}









