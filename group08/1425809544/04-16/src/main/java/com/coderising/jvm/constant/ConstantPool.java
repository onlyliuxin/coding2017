package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	
	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();
	
	
	public ConstantPool(){
		
	}
	//添加常量
	public void addConstantInfo(ConstantInfo info){
		this.constantInfos.add(info);
		
	}
	//根据索引获取常量池数据
	public ConstantInfo getConstantInfo(int index){
		return this.constantInfos.get(index);
	}
	//获取utf8的值
	public String getUTF8String(int index){
		return ((UTF8Info)this.constantInfos.get(index)).getValue();
	}
	//获取常量池大小
	public int getSize() {
		return this.constantInfos.size() -1;
	}
}
