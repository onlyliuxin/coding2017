package week6.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import week6.jvm.clz.ClassFile;
import week6.jvm.constant.ConstantInfo;
import week6.jvm.constant.ConstantPool;
import week6.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo {

	private List<LocalVariableItem> items=new ArrayList<>();
	
	private ConstantPool pool;
	
	public LocalVariableTable(int attrNameIndex, int attrLen,ConstantPool pool) {
		super(attrNameIndex, attrLen);
		this.pool=pool;
	}

	public static AttributeInfo parse(ByteCodeIterator iter,ConstantPool pool) {
		
		int attrNameIndex=iter.nextU2ToInt();
		int attrLen=iter.nextU4ToInt();
		
		int locaVarTableLen=iter.nextU2ToInt();
		
		LocalVariableTable table=new LocalVariableTable(attrNameIndex, attrLen,pool);
		
		for(int i=1;i<=locaVarTableLen;i++){
			
			LocalVariableItem item=table.new LocalVariableItem();
			
			int startPc=iter.nextU2ToInt();
			int length=iter.nextU2ToInt();
			int nameIndex=iter.nextU2ToInt();
			int descIndex=iter.nextU2ToInt();
			int index=iter.nextU2ToInt();
			
			item.setStartPc(startPc);
			item.setLength(length);
			item.setNameIndex(nameIndex);
			item.setDescIndex(descIndex);
			item.setIndex(index);
			
			table.addLocalVariableItem(item);
		}
		
		return table;
	}

	public void addLocalVariableItem(LocalVariableItem item){
		this.items.add(item);
	}
	
	@Override
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("Local Variable Table:\n");
		for(LocalVariableItem item:items){
			buffer.append("startPc:"+item.getStartPc()+"\n");
			buffer.append("length:"+item.getLength()+"\n");
			buffer.append("name:"+pool.getUTF8String(item.getNameIndex())+"\n");
			buffer.append("desc:"+pool.getUTF8String(item.getDescIndex())+"\n");
			buffer.append("slot:"+item.getIndex());
		}
		buffer.append("\n");
		
		return buffer.toString();
	}
	
	private class LocalVariableItem{
		int startPc;
		int length;
		int nameIndex;
		int descIndex;
		int index;
		private int getStartPc() {
			return startPc;
		}
		private void setStartPc(int startPc) {
			this.startPc = startPc;
		}
		private int getLength() {
			return length;
		}
		private void setLength(int length) {
			this.length = length;
		}
		private int getNameIndex() {
			return nameIndex;
		}
		private void setNameIndex(int nameIndex) {
			this.nameIndex = nameIndex;
		}
		private int getDescIndex() {
			return descIndex;
		}
		private void setDescIndex(int descIndex) {
			this.descIndex = descIndex;
		}
		private int getIndex() {
			return index;
		}
		private void setIndex(int index) {
			this.index = index;
		}	
	}	
}
