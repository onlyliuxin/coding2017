package com.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();

	private ConstantPool() {}
	
	public void addgetConstantInfo(ConstantInfo constantInfo){
		this.constantInfos.add(constantInfo);
	}
	
	public ConstantInfo getConstantInfo( int index){
		return this.constantInfos.get(index);
	}
	
	public String getUTF8String(int index){
		return ((UTF8Info)this.constantInfos.get(index)).getValue();
	}
	
	public int getSize(){
		return this.constantInfos.size() - 1;
	}
}
