package com.coderising.jvm.attr;


import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){

		int attrNameIndex = iter.nextU2ToInt();
		int attrLenght = iter.nextU4ToInt();

		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLenght);

		int localVariableTableLen = iter.nextU2ToInt();
		for (int i = 0; i < localVariableTableLen; i++) {
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
