package com.pan.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.pan.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){

		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();

		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLen);

		int attrItemSize = iter.nextU2ToInt();
		for (int i = 1; i <= attrItemSize; i++) {
			LocalVariableItem localVariableItem = new LocalVariableItem();
			localVariableItem.setStartPC(iter.nextU2ToInt());
			localVariableItem.setLength(iter.nextU2ToInt());
			localVariableItem.setNameIndex(iter.nextU2ToInt());
			localVariableItem.setDescIndex(iter.nextU2ToInt());
			localVariableItem.setIndex(iter.nextU2ToInt());
			localVariableTable.addLocalVariableItem(localVariableItem);
		}

		return localVariableTable;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
