package com.pan.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.pan.jvm.loader.ByteCodeIterator;

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
		int attrLen = iter.nextU4ToInt();
		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLen);

		int attrItemSize = iter.nextU2ToInt();
		for (int i = 1; i <= attrItemSize; i++) {
			LineNumberItem lineNumberItem = new LineNumberItem();
			lineNumberItem.setStartPC(iter.nextU2ToInt());
			lineNumberItem.setLineNum(iter.nextU2ToInt());

			lineNumberTable.addLineNumberItem(lineNumberItem);
		}
		return lineNumberTable;
	}
	
	

}
