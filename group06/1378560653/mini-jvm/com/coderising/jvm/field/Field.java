package com.coderising.jvm.field;

import com.coderising.jvm.clz.ClassFile;
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

		return name +":"+ desc;
	}
	
	
	public static Field parse(ClassFile clzFile,ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attributeCount = iter.nextU2ToInt();
		
		Field f = new Field(accessFlag, nameIndex, descIndex, clzFile.getConstantPool());
		
		if(attributeCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		
		return f;
	}

}
