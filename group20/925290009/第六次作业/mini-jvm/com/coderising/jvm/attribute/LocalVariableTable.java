package com.coderising.jvm.attribute;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	private List<LocalVariableItem> localVariableItems = new ArrayList<>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public void addLocalVaribleItem(LocalVariableItem lItem){
		this.localVariableItems.add(lItem);
	}

	public static LocalVariableTable parse(ClassFile classFile,
			ByteCodeIterator iterator) {
		iterator.back(2);
		int attrIndex = iterator.next2BytesToInt();
		int attrlen = iterator.next4BytesToInt();
		int localVariCount = iterator.next2BytesToInt();
		LocalVariableTable lTable = new LocalVariableTable(attrIndex, attrlen);
		
		if(localVariCount > 0){
			for (int i = 0; i < localVariCount; i++) {
				LocalVariableItem lItem = new LocalVariableItem();
				lItem.setStart(iterator.next2BytesToInt());
				lItem.setLenth(iterator.next2BytesToInt());
				lItem.setNameIndex(iterator.next2BytesToInt());
				lItem.setDescribeIndex(iterator.next2BytesToInt());
				lItem.setSlotIndex(iterator.next2BytesToInt());
				
				lTable.addLocalVaribleItem(lItem);
			}
		}
		return lTable;
	}

}
