package com.github.ipk2015.coding2017.minijvm.field;

import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		int accessFlag = iter.nextUNToInt(2);
		int nameIndex = iter.nextUNToInt(2);
		int descriptorIndex = iter.nextUNToInt(2);
		int attrCount = iter.nextUNToInt(2);
		if(attrCount != 0){
			throw new RuntimeException("字段的属性不为0");
		}
		return new Field(accessFlag,nameIndex,descriptorIndex,pool);
	}
	
	public String toString(){
		
		return pool.getUTF8String(nameIndex)+":"+pool.getUTF8String(descriptorIndex);
	}
}
