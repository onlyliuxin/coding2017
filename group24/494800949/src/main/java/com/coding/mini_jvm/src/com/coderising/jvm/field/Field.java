package com.coding.mini_jvm.src.com.coderising.jvm.field;


import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;


	private ConstantPool pool;

	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}


	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		int accessFlag = iter.readTwoBytesToInt();
		int nameIndex = iter.readTwoBytesToInt();
		int descIndex = iter.readTwoBytesToInt();
		int attributesCount = iter.readTwoBytesToInt();
		if (attributesCount > 0)
			throw new RuntimeException("attributeCount of field not impl");
		return new Field(accessFlag, nameIndex, descIndex, pool);
	}


	@Override
	public String toString() {
		return pool.getUTF8String(nameIndex)+":"+pool.getUTF8String(descriptorIndex);
	}
}
