package com.pan.jvm.field;


import com.pan.jvm.constant.ConstantPool;
import com.pan.jvm.constant.UTF8Info;
import com.pan.jvm.loader.ByteCodeIterator;

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

	@Override
	public String toString() {
		String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();
		String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();

		return name + ":" + desc;
	}

	public static Field parse(ConstantPool pool, ByteCodeIterator iter){

		int accessFlags = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descriptorIndex = iter.nextU2ToInt();
		int attrCount = iter.nextU2ToInt();
		System.out.println("Field Attributes Count: " + attrCount);
		Field field = new Field(accessFlags, nameIndex, descriptorIndex, pool);
		if (attrCount > 0){
			throw new RuntimeException("Attributes Count &gt 0");
		}
		return field;
	}

}
