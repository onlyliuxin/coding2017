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
		Field field = new Field(iter.nextU2Int(), iter.nextU2Int(), iter.nextU2Int(),pool);
		int attributeCount=iter.nextU2Int();
		System.out.println("attributeCount:"+attributeCount);
		for(int j=0;j<attributeCount;j++){
			int attribute_name_index = iter.nextU2Int();
			int attribute_length = iter.nextU4Integer();
			int attribute_value_index = iter.nextU2Int();
		}
		return field;
	}
	
	@Override
	public String toString() {
		String name = pool.getUTF8String(nameIndex);
		String descr = pool.getUTF8String(descriptorIndex);
		return name+":"+descr;
	}
	
}
