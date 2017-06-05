package com.coding.mini_jvm.src.com.coderising.jvm.field;


import com.coding.mini_jvm.src.com.coderising.jvm.attr.AttributeInfo;
import com.coding.mini_jvm.src.com.coderising.jvm.attr.ConstantValue;
import com.coding.mini_jvm.src.com.coderising.jvm.constant.ConstantPool;
import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;



public class Field {
	private int           accessFlag;
	private int           nameIndex;
	private int           descriptorIndex;
	private ConstantValue constValue;

	private ConstantPool pool;

	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}


	public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
		int accessFlag = iter.readTwoBytesToInt();
		int nameIndex = iter.readTwoBytesToInt();
		int descIndex = iter.readTwoBytesToInt();
		int attributesCount = iter.readTwoBytesToInt();
		Field f = new Field(accessFlag, nameIndex, descIndex, pool);
		for( int i=1; i<= attributesCount; i++){
			int attrNameIndex = iter.readTwoBytesToInt();
			String attrName = pool.getUTF8String(attrNameIndex);

			if(AttributeInfo.CONST_VALUE.equals(attrName)){
				int attrLen = iter.readFourBytesToInt();
				ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
				constValue.setConstValueIndex(iter.readTwoBytesToInt());
				f.setConstValue(constValue);
			} else{
				throw new RuntimeException("the attribute " + attrName + " has not been implemented yet.");
			}
		}
		return f;
	}


	public void setConstValue(ConstantValue constValue) {
		this.constValue = constValue;
	}

	@Override
	public String toString() {
		return pool.getUTF8String(nameIndex)+":"+pool.getUTF8String(descriptorIndex);
	}
}
