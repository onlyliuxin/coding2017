package task7.jvm.attr;


import task7.jvm.constant.ConstantPool;
import task7.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AttributeInfo {

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}


	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);
	}

	public static LocalVariableTable parse(ByteCodeIterator iter){

		int index = iter.next2Bytes();
		int len = iter.next4Bytes();

		LocalVariableTable table = new LocalVariableTable(index,len);

		int itemLen = iter.next2Bytes();

		for(int i=1; i<=itemLen; i++){
			LocalVariableItem item = new LocalVariableItem();
			item.setStartPC(iter.next2Bytes());
			item.setLength(iter.next2Bytes());
			item.setNameIndex(iter.next2Bytes());
			item.setDescIndex(iter.next2Bytes());
			item.setIndex(iter.next2Bytes());
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
