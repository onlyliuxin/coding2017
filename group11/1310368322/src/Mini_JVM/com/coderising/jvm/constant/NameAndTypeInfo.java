package com.coderising.jvm.constant;

public class NameAndTypeInfo extends ConstantInfo{
	
	public int type = ConstantInfo.NAME_AND_TYPE_INFO;
	
	private int index1;//  指向名称的索引
	private int index2;// 指向类型(描述符)的索引
	
	public NameAndTypeInfo(ConstantPool pool){
		super(pool);
	}
	
	public int getIndex1(){
		return index1;
	}
	
	public void setIndex1(int index1){
		this.index1 = index1;
	}
	
	public int getIndex2(){
		return index2;
	}
	
	public void setIndex2(int index2){
		this.index2 = index2;
	}
	
	@Override
	public int getType(){
		return type;
	}
	
	
	public String getName(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info1 = (UTF8Info)pool.getConstantInfo(index1);// 拿到 方法或字段 的 Name 的引用
		return utf8Info1.getValue(); // 返回 这个方法 或 字段 的Name值
	}
	
	public String getTypeInfo(){
		ConstantPool pool = this.getConstantPool();
		UTF8Info utf8Info2 = (UTF8Info)pool.getConstantInfo(index2);
		return utf8Info2.getValue();
	}
	
	public String toString(){
		return "(" + getName() + "," + getTypeInfo() + ")";
	}
	
}
