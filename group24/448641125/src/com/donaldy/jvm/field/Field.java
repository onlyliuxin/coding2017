package com.donaldy.jvm.field;

import com.donaldy.jvm.constant.ConstantPool;
import com.donaldy.jvm.constant.UTF8Info;
import com.donaldy.jvm.loader.ByteCodeIterator;


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
		int descriptorIndex = iter.nextU2ToInt();

		//TODO : 因无static类型变量,所以这无
		int attributesCount = iter.nextU2ToInt();

		Field file = new Field(accessFlag, nameIndex, descriptorIndex, pool);

		return file;
	}

	public String toString() {
		//System.out.println("name : " + this.nameIndex + ", desc : " + descriptorIndex);
		String description = this.pool.getUTF8String(this.descriptorIndex);
		String name = this.pool.getUTF8String(this.nameIndex);
		return name + ":"+ description;
	}
}
