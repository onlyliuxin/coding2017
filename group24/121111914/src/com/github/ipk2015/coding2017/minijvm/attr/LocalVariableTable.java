package com.github.ipk2015.coding2017.minijvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.github.ipk2015.coding2017.minijvm.constant.ConstantPool;
import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;



public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attrNameIndex = iter.nextUNToInt(2);
		int attrLength = iter.nextUNToInt(4);
		LocalVariableTable table = new LocalVariableTable(attrNameIndex,attrLength);
		int tableLen = iter.nextUNToInt(2);
		for(int i = 0;i < tableLen;i++){
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.nextUNToInt(2));
			item.setLength(iter.nextUNToInt(2));
			item.setNameIndex(iter.nextUNToInt(2));
			item.setDescIndex(iter.nextUNToInt(2));
			item.setIndex(iter.nextUNToInt(2));
			table.addLocalVariableItem(item);
		}
		return table;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
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
