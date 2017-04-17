package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;


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
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		Field field = new Field(accessFlag, nameIndex, descriptorIndex, pool);
		int attCount = iter.nextU2ToInt();
		if(attCount > 0){
			//TODO handle field attrs
			throw new RuntimeException("Field.attr not implements");
		}
		return field;
	}
	
	public String toString() {
		return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex);
	}

}
