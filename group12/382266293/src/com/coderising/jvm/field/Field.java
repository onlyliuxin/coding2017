package com.coderising.jvm.field;

import com.coderising.jvm.clz.AccessFlag;
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

	
	
	
	@Override
	public String toString() {
		return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex) ;
	}

	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		int access_flags = iter.nextU2ToInt();
		int name_index = iter.nextU2ToInt();
		int descriptor_index = iter.nextU2ToInt();
		int attrbutes_count = iter.nextU2ToInt();
		
		if (attrbutes_count != 0) {
			throw new RuntimeException("field attrbutes_count is " + attrbutes_count);
		}
		
		Field field = new Field(access_flags,name_index,descriptor_index,pool);
		
		return field;
	}

}
