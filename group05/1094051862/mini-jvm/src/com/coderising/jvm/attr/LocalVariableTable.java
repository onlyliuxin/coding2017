package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int index = iter.nextU2toInt();
		int len = iter.nextU4toInt();
		
		LocalVariableTable table = new LocalVariableTable(index, len);
		
		int itemLen = iter.nextU2toInt();
		
		for(int i = 0; i < itemLen; i++) {
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.nextU2toInt());
			item.setLength(iter.nextU2toInt());
			item.setNameIndex(iter.nextU2toInt());
			item.setDescIndex(iter.nextU2toInt());
			item.setIndex(iter.nextU2toInt());
			
			table.addLocalVariableItem(item);
		}
		
		return table;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
