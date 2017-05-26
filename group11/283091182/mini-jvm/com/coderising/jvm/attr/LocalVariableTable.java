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
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int nameIdx = iter.nextU2AsInt();
		int attrLen = iter.nextU4AsInt();
		LocalVariableTable t = new LocalVariableTable(nameIdx, attrLen);
		int itemCount = iter.nextU2AsInt();
		for(int i=0;i<itemCount;i++){
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.nextU2AsInt());
			item.setLength(iter.nextU2AsInt());
			item.setNameIndex(iter.nextU2AsInt());
			item.setDescIndex(iter.nextU2AsInt());
			item.setIndex(iter.nextU2AsInt());
			t.addLocalVariableItem(item);
		}
		return t;
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
