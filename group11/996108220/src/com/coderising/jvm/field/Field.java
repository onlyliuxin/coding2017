package com.coderising.jvm.field;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;


public class Field {
	private int accessFlag;//u2
	private int nameIndex;//u2
	private int descriptorIndex;//u2
	
	
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		return null;
	}
	public String toString() {
		UTF8Info name=(UTF8Info)(pool.getConstantInfo(nameIndex));
		UTF8Info descriptor=(UTF8Info)(pool.getConstantInfo(descriptorIndex));
		return name.getValue()+":"+descriptor.getValue();
	}

}
