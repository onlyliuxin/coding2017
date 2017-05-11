package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {

	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();
 
	public int getConstantNumber() {
		return this.constantInfos.size() - 1;
	}

	public void addConstantInfo(ConstantInfo constantInfo){
		this.constantInfos.add(constantInfo);
	}
	public Object getConstantInfo(int index) {

		return this.constantInfos.get(index);
	}

}
