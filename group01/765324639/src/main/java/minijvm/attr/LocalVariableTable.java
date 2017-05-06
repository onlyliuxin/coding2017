package minijvm.attr;


import java.util.ArrayList;
import java.util.List;

import minijvm.constant.ConstantPool;
import minijvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		
		return null;
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
