package miniJVM.attr;


import miniJVM.constant.ConstantPool;
import miniJVM.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		int attributeNameIndex = iter.nextU2ToInt();
		int attributeLength = iter.nextU4ToInt();
		LocalVariableTable table = new LocalVariableTable(attributeNameIndex, attributeLength);
		int localVariableTableLength = iter.nextU2ToInt();
		for(int i = 0; i < localVariableTableLength; i++){
			int startPC = iter.nextU2ToInt();
			int length = iter.nextU2ToInt();
			int nameIndex = iter.nextU2ToInt();
			int descriptorIndex = iter.nextU2ToInt();
			int index = iter.nextU2ToInt();
			LocalVariableItem item = new LocalVariableItem();
			table.addLocalVariableItem(item);
		}

		return table;
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
