package com.coderising.jvm.field;

import javax.management.RuntimeErrorException;

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
		int accessFlag = iter.nextU2toInt();
		int nameIndex = iter.nextU2toInt();
		int descriptorIndex = iter.nextU2toInt();
		int attributesCount = iter.nextU2toInt();
		System.out.println("Field AttributesCount: "+attributesCount);
		
		Field f = new Field(accessFlag, nameIndex, descriptorIndex, pool);
		
		if (attributesCount > 0) {
			throw new RuntimeException("Field attributes have not been parsed !");
		}
		
		return f;
	}
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		s.append(pool.getUTF8String(nameIndex));
		s.append(':');
		s.append(pool.getUTF8String(descriptorIndex));
		return s.toString();
	}

}
