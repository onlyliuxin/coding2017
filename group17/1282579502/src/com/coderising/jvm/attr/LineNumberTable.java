package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

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
		int attributeLength = iter.getNextNBytesInteger(4);
		int lineNumberTableLength = iter.getNextNBytesInteger(2);
		System.out.println("attribute length: " + attributeLength);
		System.out.println("line number table length: " + lineNumberTableLength);
		LineNumberTable table = new LineNumberTable(attributeLength, lineNumberTableLength);
		for(int i = 0; i<lineNumberTableLength; i++){
			LineNumberItem item = new LineNumberItem();
			item.setStartPC(iter.getNextNBytesInteger(2));
			item.setLineNum(iter.getNextNBytesInteger(2));
			table.addLineNumberItem(item);
			System.out.println("start pc: " + item.getStartPC() + " line number: " + item.getLineNum());
		}
		return table;
	}
	
	

}
