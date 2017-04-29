package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		
		int nameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		LocalVariableTable localVariableTable = new LocalVariableTable(nameIndex,attrLen);
		
		int tableLen = iter.nextU2ToInt();
		
		for (int i = 0; i < tableLen; i++) {
			
			int startPC = iter.nextU2ToInt();
			int len = iter.nextU2ToInt();
			int nameindex = iter.nextU2ToInt();
			int descrIndex = iter.nextU2ToInt();
			int index = iter.nextU2ToInt();
			
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(startPC);
			item.setNameIndex(nameindex);
			item.setLength(len);
			item.setDescIndex(descrIndex);
			item.setIndex(index);
			localVariableTable.addLocalVariableItem(item);
		}
		return localVariableTable;
	}
			
}
