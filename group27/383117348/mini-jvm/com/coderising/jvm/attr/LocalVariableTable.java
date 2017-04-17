package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo {

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public static LocalVariableTable parse(ByteCodeIterator iter) {
		int attributeNameIndex = iter.nextU2Int();
		int attributeLength = iter.nextU4Integer();

		LocalVariableTable localVariableTable = new LocalVariableTable(attributeNameIndex, attributeLength);

		int localVariableTableLength = iter.nextU2Int();
		for (int i = 0; i < localVariableTableLength; i++) {
			LocalVariableItem localVariableItem = new LocalVariableItem();
			localVariableItem.setStartPC(iter.nextU2Int());
			localVariableItem.setLength(iter.nextU2Int());
			localVariableItem.setNameIndex(iter.nextU2Int());
			localVariableItem.setDescIndex(iter.nextU2Int());
			localVariableItem.setIndex(iter.nextU2Int());
			localVariableTable.addLocalVariableItem(localVariableItem);
		}

		return localVariableTable;
	}

	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);
	}

}
