package com.coding.basic.homework_04.jvm.constant;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.homework_04.jvm.info.UTF8Info;

public class ConstantPool {
	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();
	
	public ConstantPool() {

	}
	
	public void addConstantInfo(ConstantInfo info){
		constantInfos.add(info);
	}
	
	public int getSize() {
		return this.constantInfos.size() - 1;
	}


	public ConstantInfo getConstantInfo(int type) {
		
		return this.constantInfos.get(type);
	}
	
	public String getUTF8String(int index){
		return ((UTF8Info)this.constantInfos.get(index)).getValue();
	}

}
