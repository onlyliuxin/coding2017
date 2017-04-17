package com.coderising.jvm.constant;

public class ClassInfo extends ConstantInfo{
	
	private int type = ConstantInfo.CLASS_INFO;//  表示该常量为 类或接口的符号引用
	private int utf8Index;//  CONSTANT_Class_info 型常量的 （表）结构中的 name_index， name_index 指向常量池中一个 CONSTANT_Utf8_info 类型的常量
	
	public ClassInfo(ConstantPool pool){
		super(pool);
	}
	
	public int getUtf8Index(){
		return utf8Index;
	}
	
	public void setUtf8Index(int utf8Index){
		this.utf8Index = utf8Index;
	}
	
	@Override
	public int getType(){
		return type;
	}
	
	// 返回类名
	public String getClassName(){
		int index = getUtf8Index();
		UTF8Info utf8Info = (UTF8Info)constantPool.getConstantInfo(index);
		return utf8Info.getValue();
	}
	
	
}
