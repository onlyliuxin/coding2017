package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
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
	    Field field = new Field(iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt(), pool);

	    int attributeCount = iter.nextU2ToInt();
	    if (attributeCount < 0) return null;
	    return field;
	}

	@Override
	public String toString() {
	    return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex);
	}
}
