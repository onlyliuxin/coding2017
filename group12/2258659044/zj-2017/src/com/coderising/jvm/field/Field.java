package com.coderising.jvm.field;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
		
	private List<AttributeInfo> attributeInfos = new ArrayList<>();
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}
	
	public int getAccessFlag() {
		return accessFlag;
	}

	public void setAccessFlag(int accessFlag) {
		this.accessFlag = accessFlag;
	}
		
	public List<AttributeInfo> getAttributeInfos() {
		return attributeInfos;
	}

	public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
		this.attributeInfos = attributeInfos;
	}

	@Override
	public String toString() {
				
		String fieldName = pool.getUTF8String(nameIndex);
		String fieldDesc = pool.getUTF8String(descriptorIndex);
		
		return (fieldName+":"+fieldDesc);
	}

	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
				
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		
		Field field = new Field(accessFlag,nameIndex,descriptorIndex,pool);				
		field.setAttributeInfos(AttributeInfo.parseAttributes(pool,iter));
		
		return field;
	}
	
}
