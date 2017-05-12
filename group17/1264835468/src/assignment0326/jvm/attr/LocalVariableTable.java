package assignment0326.jvm.attr;


import assignment0326.jvm.constant.ConstantPool;
import assignment0326.jvm.loader.ByteCodeIterator;

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
        int attrNameIndex = iter.nextU2ToInt();
        int attrLength = iter.nextU4ToInt();
        int localVarTableLength = iter.nextU2ToInt();
        LocalVariableTable localVariableTable = new LocalVariableTable(attrNameIndex, attrLength);
        for (int i = 0; i < localVarTableLength; i++) {
            LocalVariableItem localVariableItem = new LocalVariableItem();
            localVariableItem.setStartPC(iter.nextU2ToInt());
            localVariableItem.setLength(iter.nextU2ToInt());
            localVariableItem.setNameIndex(iter.nextU2ToInt());
            localVariableItem.setDescIndex(iter.nextU2ToInt());
            localVariableItem.setIndex(iter.nextU2ToInt());
            localVariableTable.addLocalVariableItem(localVariableItem);
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
