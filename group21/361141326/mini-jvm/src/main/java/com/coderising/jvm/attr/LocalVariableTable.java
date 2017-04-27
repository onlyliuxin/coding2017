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
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iterator){
	    int attributeNameIndex = iterator.nextU2ToInt();
	    int attributeLength = iterator.nextU4ToInt();

		LocalVariableTable localVariableTable = new LocalVariableTable(attributeNameIndex, attributeLength);

		int localVariableLength = iterator.nextU2ToInt();
		for (int i = 0; i < localVariableLength; i++) {
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iterator.nextU2ToInt());
			item.setLength(iterator.nextU2ToInt());
			item.setNameIndex(iterator.nextU2ToInt());
			item.setDescIndex(iterator.nextU2ToInt());
			item.setDescIndex(iterator.nextU2ToInt());

			localVariableTable.addLocalVariableItem(item);
		}

		return localVariableTable;
	}

	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Local Variable Table:\n");
		for(LocalVariableItem item : items){
			buffer.append("startPC:"+item.getStartPC()).append(",");
			buffer.append("name:"+pool.getUTF8String(item.getNameIndex())).append(",");
			buffer.append("desc:"+pool.getUTF8String(item.getDescIndex())).append(",");
			buffer.append("slotIndex:"+ item.getIndex()).append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
	}
}
