package com.coding.jvm.field;

import com.coding.jvm.attr.AttributeInfo;
import com.coding.jvm.constant.ConstantPool;
import com.coding.jvm.loader.ByteCodeIterator;



public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		Field field = new Field(iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt(), pool);
		int attrCount = iter.nextU2ToInt();
		for (int i = 0; i < attrCount; i++) {
			AttributeInfo.parse(iter);
		}
		return field;
	}
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public Field(ConstantPool pool) {
		this.pool = pool;
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

	public ConstantPool getPool() {
		return pool;
	}

	public void setPool(ConstantPool pool) {
		this.pool = pool;
	}

	@Override
	public String toString() {
		return pool.getUTF8String(nameIndex)+":"+pool.getUTF8String(descriptorIndex);
	}
	
	

}
