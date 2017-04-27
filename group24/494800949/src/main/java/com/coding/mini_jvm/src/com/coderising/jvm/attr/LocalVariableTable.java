package com.coding.mini_jvm.src.com.coderising.jvm.attr;


import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		iter.back(ByteCodeIterator.U2);
		int attrNameIdex = iter.readTwoBytesToInt();
		int attrLen = iter.readFourBytesToInt();
		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIdex, attrLen);
		int varCount = iter.readTwoBytesToInt();
		for (int i = 0; i < varCount; i++) {
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.readTwoBytesToInt());
			item.setLength(iter.readTwoBytesToInt());
			item.setNameIndex(iter.readTwoBytesToInt());
			item.setDescIndex(iter.readTwoBytesToInt());
			item.setIndex(iter.readTwoBytesToInt());
			localVariableTable.addLocalVariableItem(item);
		}
		return localVariableTable;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
