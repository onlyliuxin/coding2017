package com.donaldy.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.donaldy.jvm.constant.ConstantPool;

import com.donaldy.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int localVariableTableLen = iter.nextU2ToInt();

		LocalVariableTable lvTable = new LocalVariableTable(attrNameIndex, attrLen);

		for (int i = 0 ; i < localVariableTableLen; ++i) {
			LocalVariableItem lvItem = new LocalVariableItem();
			lvItem.setStartPC(iter.nextU2ToInt());
			lvItem.setLength(iter.nextU2ToInt());
			lvItem.setNameIndex(iter.nextU2ToInt());
			lvItem.setDescIndex(iter.nextU2ToInt());
			lvItem.setIndex(iter.nextU2ToInt());

			lvTable.addLocalVariableItem(lvItem);
		}


		return lvTable;
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
