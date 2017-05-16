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
		int accessflag = iter.nextU2ToInt();
		int nameindex = iter.nextU2ToInt();
		int descriptorindex = iter.nextU2ToInt();
		int attributecount = iter.nextU2ToInt();
		if (attributecount > 0) {
			throw new RuntimeException("该字段中有属性" + attributecount + "个，未进行解析");
		}
		Field field = new Field(accessflag, nameindex, descriptorindex, pool);
		return field;

	}
	public String toString() {
		return pool.getUTF8String(this.nameIndex)+":"+pool.getUTF8String(this.descriptorIndex);
	}

}
