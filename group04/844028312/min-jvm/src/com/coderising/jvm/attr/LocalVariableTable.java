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
		int attrNameIndex=iter.nextU2toInt();
		int attrLen=iter.nextU4toInt();
		int itemNum=iter.nextU2toInt();
		LocalVariableTable localTable=new LocalVariableTable(attrNameIndex,attrLen);
		for(int i=0;i<itemNum;i++){
			int startPC=iter.nextU2toInt();
			int length=iter.nextU2toInt();
			int nameIndex=iter.nextU2toInt();
			int descIndex=iter.nextU2toInt();
			int index=iter.nextU2toInt();
			LocalVariableItem item=new LocalVariableItem();
			item.setStartPC(startPC);
			item.setIndex(index);
			item.setDescIndex(descIndex);
			item.setLength(length);
			item.setNameIndex(nameIndex);
			localTable.addLocalVariableItem(item);
		}
		return localTable;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
