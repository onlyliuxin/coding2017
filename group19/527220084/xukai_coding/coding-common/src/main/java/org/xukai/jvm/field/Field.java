package org.xukai.jvm.field;


import org.xukai.jvm.constant.ConstantPool;
import org.xukai.jvm.constant.UTF8Info;
import org.xukai.jvm.loader.ByteCodeIterator;

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

	public String toString(){
		return getFieldName() +":"+  getFieldDescription();
	}

	public String getFieldName(){
		return ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
	}

	public String getFieldDescription(){
		return ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
	}
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		
		return null;
	}

}
