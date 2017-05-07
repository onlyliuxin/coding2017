package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2ToInt();
		 
		int attrLen = iter.nextU4ToInt();
		 
		int local_variable_table_length = iter.nextU2ToInt();
		
		LocalVariableTable lvt = new LocalVariableTable(attrNameIndex, attrLen);
		
		
		for (int i = 0; i < local_variable_table_length; i++) {
			
			LocalVariableItem lvi = new LocalVariableItem();
			
			int start_pc = iter.nextU2ToInt();
			lvi.setStartPC(start_pc);
			
			int length = iter.nextU2ToInt();
			lvi.setLength(length);
			
			int name_index = iter.nextU2ToInt();
			lvi.setNameIndex(name_index);
			
			int desc_index = iter.nextU2ToInt();
			lvi.setDescIndex(desc_index);
			
			int index = iter.nextU2ToInt();
			lvi.setIndex(index);
			
			lvt.addLocalVariableItem(lvi);
		}
		 
		return lvt;
	}
	
	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
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
