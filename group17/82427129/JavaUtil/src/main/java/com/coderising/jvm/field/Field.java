package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;//u2  access_flags
	private int nameIndex;//u2 name_index
	private int descriptorIndex;//u2 descriptor_index
	//attr 属性表信息首次写jvm先放弃，以后再加
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		return null;
	}

	public int getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) {
		this.nameIndex = nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public void setDescriptorIndex(int descriptorIndex) {
		this.descriptorIndex = descriptorIndex;
	}
	
}
