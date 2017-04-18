package org.xukai.jvm.attr;


import org.xukai.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attributeNameIndex = iter.nextToInt(2);
		int attributeLength = iter.nextToInt(4);
		LocalVariableTable localVariableTable = new LocalVariableTable(attributeNameIndex, attributeLength);
		int localVariableTableLength = iter.nextToInt(2);
		if (localVariableTableLength > 0) {
			for (int i = 0; i < localVariableTableLength; i++) {
				int startPc = iter.nextToInt(2);
				int length = iter.nextToInt(2);
				int nameIndex = iter.nextToInt(2);
				int descriptorIndex = iter.nextToInt(2);
				int index = iter.nextToInt(2);
				LocalVariableItem lineNumberItem = new LocalVariableItem();
				lineNumberItem.setStartPC(startPc);
				lineNumberItem.setLength(length);
				lineNumberItem.setNameIndex(nameIndex);
				lineNumberItem.setLength(descriptorIndex);
				lineNumberItem.setLength(index);
				localVariableTable.addLocalVariableItem(lineNumberItem);
			}
		}
		return localVariableTable;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
