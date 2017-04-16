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
		int accessFlag=iter.nextU2ToInt();
		int nameIndex=iter.nextU2ToInt();
		int descriptorIndex=iter.nextU2ToInt();
		int attributesCount=iter.nextU2ToInt();
		System.out.println("Field's attributesCount is:"+attributesCount);
		Field field=new Field(accessFlag, nameIndex, descriptorIndex, pool);
		if(attributesCount>0){
			throw new RuntimeException("Field attributes have not been implemented yet");
		}
		return field;
	}
	public String toString(){
		return this.pool.getUTF8String(nameIndex)+":"+this.pool.getUTF8String(descriptorIndex);
	}

}
