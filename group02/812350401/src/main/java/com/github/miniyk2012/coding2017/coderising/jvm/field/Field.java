package com.github.miniyk2012.coding2017.coderising.jvm.field;


import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;
import com.github.miniyk2012.coding2017.coderising.jvm.constant.UTF8Info;
import com.github.miniyk2012.coding2017.coderising.jvm.loader.ByteCodeIterator;

public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	private ConstantPool pool;
	
	public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public String toString() {
		// String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		String name = pool.getUTF8String(nameIndex);
		
		//String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		String desc = pool.getUTF8String(descriptorIndex);
		return name +":"+ desc;
	}
	
	
	public static Field parse(ConstantPool pool, ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2toInt();
		int nameIndex = iter.nextU2toInt();
		int descIndex = iter.nextU2toInt();
		int attribCount = iter.nextU2toInt();
		//System.out.println("field attribute count:"+ attribCount);
		
		Field f = new Field(accessFlag, nameIndex, descIndex, pool);
		
		if(attribCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		
		return f;
	}

}
