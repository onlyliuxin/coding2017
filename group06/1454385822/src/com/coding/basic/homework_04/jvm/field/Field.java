package com.coding.basic.homework_04.jvm.field;

import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.info.UTF8Info;
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class Field {

	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private ConstantPool constantPool;
	
	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
		this.accessFlag = accessFlag;
		this.descriptorIndex = descriptorIndex;
		this.nameIndex = nameIndex;
		this.constantPool = constantPool;
	}
	
	public static Field parse(ConstantPool constantPool, ByteCodeIterator iterator) {
		int accessFlag = iterator.nextU2ToInt();
		int nameIndex = iterator.nextU2ToInt();
		int descriptorIndex = iterator.nextU2ToInt();
		//TODO这里字段没有属性，没做解析工作
		int attributeCount = iterator.nextU2ToInt();
		Field field = new Field(accessFlag, nameIndex, descriptorIndex, constantPool);
		
		if(attributeCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		return field;
	}
	public String toString() {
		String name = ((UTF8Info)constantPool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)constantPool.getConstantInfo(this.descriptorIndex)).getValue();
		return name +":"+ desc;
	}

}
