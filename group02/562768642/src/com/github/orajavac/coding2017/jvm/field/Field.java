package com.github.orajavac.coding2017.jvm.field;

import com.github.orajavac.coding2017.jvm.constant.ConstantPool;
import com.github.orajavac.coding2017.jvm.loader.ByteCodeIterator;

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
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		Field f = new Field(accessFlag,nameIndex,descIndex,pool);
		return f;
	}
}
