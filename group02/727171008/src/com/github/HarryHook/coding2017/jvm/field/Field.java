
package com.github.HarryHook.coding2017.jvm.field;

import com.github.HarryHook.coding2017.jvm.constant.ConstantPool;
import com.github.HarryHook.coding2017.jvm.loader.ByteCodeIterator;
import com.sun.istack.internal.Pool;
import com.github.HarryHook.coding2017.jvm.constant.UTF8Info;

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

    public static Field parse(ConstantPool pool, ByteCodeIterator iter) {
	int accessFlag = iter.nextU2ToInt();
	int nameIndex = iter.nextU2ToInt();
	int descriptorIndex = iter.nextU2ToInt();
	int attributesCount = iter.nextU2ToInt();
	System.out.println("field attributes Count: " + attributesCount);
	Field f = new Field(accessFlag, nameIndex, descriptorIndex, pool);
	if(attributesCount > 0) {
	    throw new RuntimeException("Field has not complemented attribute");
	}
	return f;
    }
    
    public String toString() {
	String name = ((UTF8Info) pool.getConstantInfo(this.nameIndex)).getValue();
	String desc = ((UTF8Info) pool.getConstantInfo(this.descriptorIndex)).getValue();
	return name +":" + desc;
    }
    

}