package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{
	private List<LocalVariableItem>  localVariableItems = new ArrayList<>();

	public List<LocalVariableItem> getLocalVariableItems() {
		return localVariableItems;
	}

	public void addLocalVariableItem(LocalVariableItem localVariableItem) {
		this.localVariableItems.add(localVariableItem);
	}

	public LocalVariableTable(int attrNameIndex, int attrLength) {
		super(attrNameIndex, attrLength);
	}

	public static LocalVariableTable parse(ByteCodeIterator it, ConstantPool constantPool) {
		int attrNameIndex = it.next2ByteToInt();
		int attrLength = it.next4ByteToInt();
		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLength);
		
		int localVariableTableLength = it.next2ByteToInt();
		while(localVariableTableLength > 0) {
			int startPc = it.next2ByteToInt();
			int length = it.next2ByteToInt();
			int nameIndex = it.next2ByteToInt();
			int descIndex = it.next2ByteToInt();
			int index = it.next2ByteToInt();
			localVariableTable.addLocalVariableItem(new LocalVariableItem(startPc, length, nameIndex, descIndex, index));
			localVariableTableLength--;
		}
		return localVariableTable;
	}

}
