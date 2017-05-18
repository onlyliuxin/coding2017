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
		Field field=new Field(iter.nextU2toInt(),iter.nextU2toInt(),iter.nextU2toInt(),pool);
		int attrNum=iter.nextU2toInt();
		System.out.println("attrNum:" + attrNum);

		// TODO : 如果field有属性个数, 这里需要解析
		return field;
	}
	
	public String toString(){
		String nameIndex=this.pool.getUTF8String(this.nameIndex);
		String descriptorIndex=this.pool.getUTF8String(this.descriptorIndex);
		return nameIndex+":"+descriptorIndex;
		
	}

}
