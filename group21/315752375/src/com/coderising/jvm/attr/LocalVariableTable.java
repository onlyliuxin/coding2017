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
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		int localVarTableLen=iter.nextU2ToInt();
		LocalVariableTable localVariableTable=new LocalVariableTable(attrNameIndex, attrLen);
		for(int i=0;i<localVarTableLen;i++){
			LocalVariableItem localVariableItem=new LocalVariableItem();
			int startPC=iter.nextU2ToInt();
			int length=iter.nextU2ToInt();
			int nameIndex=iter.nextU2ToInt();
			int descIndex=iter.nextU2ToInt();
			int index=iter.nextU2ToInt();
			localVariableItem.setStartPC(startPC);
			localVariableItem.setLength(length);
			localVariableItem.setNameIndex(nameIndex);
			localVariableItem.setDescIndex(descIndex);
			localVariableItem.setIndex(index);
			localVariableTable.addLocalVariableItem(localVariableItem);
			
		}
		return localVariableTable;
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
