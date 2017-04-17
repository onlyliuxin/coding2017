package com.github.miniyk2012.coding2017.coderising.jvm.attr;


import com.github.miniyk2012.coding2017.coderising.jvm.constant.ConstantPool;
import com.github.miniyk2012.coding2017.coderising.jvm.loader.ByteCodeIterator;

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
		
		int index = iter.nextU2toInt();
		int len = iter.nextU4toInt();
		
		LocalVariableTable table = new LocalVariableTable(index,len);
		
		int itemLen = iter.nextU2toInt();
		
		for(int i=1; i<=itemLen; i++){
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
