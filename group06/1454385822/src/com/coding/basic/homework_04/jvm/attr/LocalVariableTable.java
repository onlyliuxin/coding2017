package com.coding.basic.homework_04.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coding.basic.homework_04.jvm.constant.ConstantPool;
import com.coding.basic.homework_04.jvm.util.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attrNameIndex = iter.nextU2ToInt();
		int attrLength = iter.nextU4ToInt();
		LocalVariableTable table = new LocalVariableTable(attrNameIndex, attrLength);
		int localVariableTableLength = iter.nextU2ToInt();
		for(int i=0; i<localVariableTableLength; i++){
			int startPC = iter.nextU2ToInt();
			int length = iter.nextU2ToInt();
			int nameIndex = iter.nextU2ToInt();
			int descriptorIndex = iter.nextU2ToInt();
			int index = iter.nextU2ToInt();
			LocalVariableItem item = new LocalVariableItem(startPC, length, nameIndex, descriptorIndex, index);
			table.addLocalVariableItem(item);
		}
		return table;
	}
	
	private static class LocalVariableItem{
		int startPC;
		int length;
		int nameIndex;
		int descriptorIndex;
		int index;
		public LocalVariableItem(int startPC, int length, int nameIndex, int descriptorIndex, int index) {
			this.startPC = startPC;
			this.length = length;
			this.nameIndex = nameIndex;
			this.descriptorIndex = descriptorIndex;
			this.index = index;
		}
		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public int getNameIndex() {
			return nameIndex;
		}
		public void setNameIndex(int nameIndex) {
			this.nameIndex = nameIndex;
		}
		public int getDescriptorIndex() {
			return descriptorIndex;
		}
		public void setDescriptorIndex(int descriptorIndex) {
			this.descriptorIndex = descriptorIndex;
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		
	}
	
	
	public String toString(ConstantPool pool){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Local Variable Table:\n");
		for(LocalVariableItem item : items){
			buffer.append("startPC:"+item.getStartPC()).append(",");
			buffer.append("name:"+pool.getUTF8String(item.getNameIndex())).append(",");
			buffer.append("desc:"+pool.getUTF8String(item.getDescriptorIndex())).append(",");
			buffer.append("slotIndex:"+ item.getIndex()).append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
	}
}
