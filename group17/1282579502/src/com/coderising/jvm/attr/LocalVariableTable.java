package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantPool;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
public static LocalVariableTable parse(ByteCodeIterator iter, ClassFile clz, int nameIndex){
		LocalVariableTable attrInfo = null;
		int attributeLength = iter.getNextNBytesInteger(4);
		int localVarTableLength = iter.getNextNBytesInteger(2);
		System.out.println("attribute length: " + attributeLength);
		System.out.println("local variable table length: " + localVarTableLength); 
		attrInfo = new LocalVariableTable(nameIndex, attributeLength);
		for(int i = 0;i<localVarTableLength; i++){
			LocalVariableItem item = LocalVariableItem.parse(iter, clz.getConstantPool());
			System.out.println("local variable table item: " + item);
			attrInfo.addLocalVariableItem(item);
		}
		
		return attrInfo;
	}
}
