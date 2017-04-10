package com.github.miniyk2012.coding2017.coderising.jvm.constant;

import com.github.miniyk2012.coding2017.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	
	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();

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
