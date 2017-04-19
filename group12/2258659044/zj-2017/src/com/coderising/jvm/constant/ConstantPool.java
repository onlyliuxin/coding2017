package com.coderising.jvm.constant;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;

public class ConstantPool {
	
	private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();
	
	private ClassFile clzFile;
	
	public ConstantPool(ClassFile clzFile){
		this.clzFile = clzFile;
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
	public int getSize() {		
		return this.constantInfos.size() -1;
	}

	public List<ConstantInfo> getConstantInfos() {
		return constantInfos;
	}

	public void setConstantInfos(List<ConstantInfo> constantInfos) {
		this.constantInfos = constantInfos;
	}

	public ClassFile getClzFile() {
		return clzFile;
	}
	
}
