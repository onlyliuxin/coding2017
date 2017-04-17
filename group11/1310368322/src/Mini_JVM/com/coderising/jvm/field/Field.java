package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;

	private ConstantPool pool;
	public int getNameIndex(){
		return nameIndex;
	}
	public int getDescIndex(){
		return descriptorIndex;
	}
	public String toString(){
		return pool.getUTF8String(nameIndex) + ":" + pool.getUTF8String(descriptorIndex);
	}
	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool){
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}
	
	
	public static Field parse(ConstantPool pool, ByteCodeIterator iter){
		int accessFlag = iter.nextU2toInt();
		int nameIndex = iter.nextU2toInt();
		int descriptorIndex = iter.nextU2toInt();
		int attrLen = iter.nextU2toInt();
		if(attrLen > 0){
			throw new RuntimeException("Field attributes has not been implemented");
		}
		Field field = new Field(accessFlag,nameIndex,descriptorIndex,pool);
				
		return field;
	}
	
	
}
