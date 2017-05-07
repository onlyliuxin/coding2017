package com.coderising.jvm.field;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.InvalidAttributeInfoException;
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

	public static Field parse(ConstantPool pool,ByteCodeIterator iter) throws InvalidAttributeInfoException{
		int accessFlag = iter.getNextNBytesInteger(2);
		int nameIndex = iter.getNextNBytesInteger(2);
		int descriptorIndex = iter.getNextNBytesInteger(2);
		int attributeCount = iter.getNextNBytesInteger(2);
		List<AttributeInfo> attributeList = new ArrayList<>();
		for(int i = 0; i<attributeCount; i++){
			
		}
		if(attributeCount>0){
			throw new InvalidAttributeInfoException("Attribute infomation is NOT valid.");
		}
		Field newField = new Field(accessFlag, nameIndex, descriptorIndex,pool);
		return newField;
	}
	
	public String toString(){
		return pool.getUTF8String(nameIndex) + ":" +pool.getUTF8String( descriptorIndex);
	}

}
