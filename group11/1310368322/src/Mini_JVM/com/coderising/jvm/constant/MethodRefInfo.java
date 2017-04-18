package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo{
	
	private int type = ConstantInfo.METHOD_INFO;
	private int classInfoIndex;// 指向 声明该方法的类的索引
	private int nameAndTypeIndex;// 指向包含该方法 名称和类型的索引
	
	public MethodRefInfo(ConstantPool pool){
		super(pool);
	}
	
	@Override
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
	
	public String getClassName(){
		ConstantPool pool = this.getConstantPool();
		ClassInfo clzInfo = (ClassInfo)pool.getConstantInfo(this.classInfoIndex);
		return clzInfo.getClassName();
	}
	
	public String getMethodName(){
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.nameAndTypeIndex);
		return typeInfo.getName();
	}
	
	public String getParamAndReturnType(){
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.nameAndTypeIndex);
		return typeInfo.getTypeInfo();
	}

}
