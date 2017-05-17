package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLineNum() {
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
	}
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public static LineNumberTable parse(ByteCodeIterator iter){

		int attrNameIndex = iter.nextU2ToInt();
		int attrLenght = iter.nextU4ToInt();

		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLenght);

		int lineNumberTableLenght = iter.nextU2ToInt();
		for (int i = 0; i < lineNumberTableLenght; i++) {
			LineNumberItem lineNumberItem = new LineNumberItem();
			lineNumberItem.setStartPC(iter.nextU2ToInt());
			lineNumberItem.setLineNum(iter.nextU2ToInt());
		}

		return lineNumberTable;
	}
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		buffer.append("Line Number Table:\n");
		for(LineNumberItem item : items){
			buffer.append("startPC:"+item.getStartPC()).append(",");
			buffer.append("lineNum:"+item.getLineNum()).append("\n");
		}
		buffer.append("\n");
		return buffer.toString();
	}
	
	

}
