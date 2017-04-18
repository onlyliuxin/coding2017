package com.coderising.jvm.constant;

public class MethodRefInfo extends ConstantInfo {
	
	private int type = ConstantInfo.METHOD_INFO;
	
	private int classIndex;
	private int nameAndTypeIndex;
	
	public MethodRefInfo(ConstantPool pool) {
		super(pool);
	}

	public int getType() {
		return type;
	}
	
	public int getClassInfoIndex() {
		return classIndex;
	}
	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}
	public int getNameAndTypeIndex() {
		return nameAndTypeIndex;
	}
	public void setNameAndTypeIndex(int nameAndTypeIndex) {
		this.nameAndTypeIndex = nameAndTypeIndex;
	}

	@Override
	public String toString(){
	
		return getClassName() +" : "+ this.getMethodName() + " : " + this.getParamAndReturnType() ;
	}
	public String getClassName(){
		ConstantPool pool = this.getConstantPool();		
		ClassInfo clzInfo = (ClassInfo)pool.getConstantInfo(this.getClassInfoIndex());
		return clzInfo.getClassName();
	}
	
	public String getMethodName(){
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getName();
	}
	
	public String getParamAndReturnType(){
		ConstantPool pool = this.getConstantPool();
		NameAndTypeInfo  typeInfo = (NameAndTypeInfo)pool.getConstantInfo(this.getNameAndTypeIndex());
		return typeInfo.getTypeInfo();
	}
	
	
	
}
