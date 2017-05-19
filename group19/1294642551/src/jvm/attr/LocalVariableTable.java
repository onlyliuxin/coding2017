package jvm.attr;


import java.util.ArrayList;
import java.util.List;

import jvm.constant.ConstantPool;

import jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		iter.back(2);
		int attrNameIndex = iter.nextU2ToInt();
		int attrLen = iter.nextU4ToInt();
		int itemLen = iter.nextU2ToInt();
		LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLen);
		
		for(int i = 0; i < itemLen; i++){
			int startPC = iter.nextU2ToInt();
			int length = iter.nextU2ToInt();
			int nameIndex = iter.nextU2ToInt();
			int descIndex = iter.nextU2ToInt();
			int slotIndex = iter.nextU2ToInt();
			LocalVariableItem item = new LocalVariableItem(startPC, length, nameIndex, descIndex, slotIndex);
			localVariableTable.addLocalVariableItem(item);
			
		}
		
		return localVariableTable;
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
