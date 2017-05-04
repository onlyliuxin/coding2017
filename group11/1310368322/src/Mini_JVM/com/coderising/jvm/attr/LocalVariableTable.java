package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter, int subAttrName, int subAttrLen){
		
		LocalVariableTable locVarTable = new LocalVariableTable(subAttrName, subAttrLen);
		int localVarTableLen = iter.nextU2toInt();
		for(int i = 0; i < localVarTableLen; i++){
			int startPC = iter.nextU2toInt();
			int length = iter.nextU2toInt();
			int nameIndex = iter.nextU2toInt();
			int descIndex = iter.nextU2toInt();
			int index = iter.nextU2toInt();
			LocalVariableItem locVarItem = new LocalVariableItem();
			locVarItem.setStartPC(startPC);
			locVarItem.setLength(length);
			locVarItem.setNameIndex(nameIndex);
			locVarItem.setDescIndex(descIndex);
			locVarItem.setIndex(index);
			locVarTable.addLocalVariableItem(locVarItem);
		}
		
		return locVarTable;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}

}
