package com.coderising.jvm.constant;

import java.util.LinkedList;
import java.util.List;

public class ConstantPool {
	
	private List<ConstantInfo> constantInfos = new LinkedList<ConstantInfo>();
	
	
	public ConstantPool(){
		
	}
	public void addConstantInfo(ConstantInfo info){
		
		this.constantInfos.add(info);
		
	}
	
	public ConstantInfo getConstantInfo(int index){
		return this.constantInfos.get(index);
	}
	public String getUTF8String(int index){
		return ((UTF8Info)this.constantInfos.get(index)).getValue();
	}
	public Object getSize() {		
		return this.constantInfos.size() -1;
	}
}
