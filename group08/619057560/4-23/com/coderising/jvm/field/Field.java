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

	public String toString() {
		String name = pool.getUTF8String(nameIndex);
		String desc = pool.getUTF8String(descriptorIndex);
		return name + ":" + desc;
	}
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		Field field = new Field(iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt(), pool);
		int attribCount = iter.nextU2ToInt();
		if (attribCount > 0) {
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		return field;
	}

}
