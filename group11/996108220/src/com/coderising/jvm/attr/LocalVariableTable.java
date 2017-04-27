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
	
	public void parse(ByteCodeIterator iter){
		int count=iter.nextU2ToInt();
		for (int i = 0; i < count; i++) {
			LocalVariableItem item=new LocalVariableItem();
			item.setStartPC(iter.nextU2ToInt());
			item.setLength(iter.nextU2ToInt());
			item.setNameIndex(iter.nextU2ToInt());
			item.setDescIndex(iter.nextU2ToInt());
			item.setIndex(iter.nextU2ToInt());
			this.addLocalVariableItem(item);
		}
		
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
