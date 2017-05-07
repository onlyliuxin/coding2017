package com.coding.basic.homework_04.jvm.info;

import com.coding.basic.homework_04.jvm.constant.ConstantInfo;
import com.coding.basic.homework_04.jvm.constant.ConstantPool;

public class ClassInfo extends ConstantInfo {

	public ClassInfo(ConstantPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	public ClassInfo() {
		// TODO Auto-generated constructor stub
	}

	private  int tag = ConstantInfo.CLASS_INFO;
	
	private int utf8Index;

	public int getTag() {
		return tag;
	}


	public int getUtf8Index() {
		return utf8Index;
	}

	public void setUtf8Index(int name_index) {
		this.utf8Index = name_index;
	}
	public String getClassName() {
		int utf8Index = getUtf8Index();
		UTF8Info utf8Info = (UTF8Info)pool.getConstantInfo(utf8Index);
		return utf8Info.getValue();
	}
	@Override
	public int getType() {
		return tag;
	}
	@Override
	public void accept(Visitor visitor) {
		visitor.visitClassInfo(this);
	}
	
	
	
	
	
	
}
