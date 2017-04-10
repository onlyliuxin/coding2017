package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
	private List<ConstantInfo> cl = new ArrayList<ConstantInfo>();
	private int constant_pool_size;

	public int getConstant_pool_size() {
		return constant_pool_size;
	}

	public void setConstant_pool_size(int constant_pool_size) {
		this.constant_pool_size = constant_pool_size;
	}
	
	public void addConstantInfo(ConstantInfo e){
		cl.add(e);
	}

	public ConstantInfo getConstantInfo(int index) {
		return cl.get(index);
	}

	public int getSize() {
		return cl.size() - 1;// 减去常量池的长度一项
	}
}
