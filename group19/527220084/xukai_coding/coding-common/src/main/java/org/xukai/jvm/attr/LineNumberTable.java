package org.xukai.jvm.attr;

import org.xukai.jvm.loader.ByteCodeIterator;

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
		int attributeNameIndex = iter.nextToInt(2);
		int attributeLength = iter.nextToInt(4);
		LineNumberTable lineNumberTable = new LineNumberTable(attributeNameIndex, attributeLength);
		int lineNumberTableLengh = iter.nextToInt(2);
		if (lineNumberTableLengh > 0) {
			for (int i = 0; i < lineNumberTableLengh; i++) {
				int startPc = iter.nextToInt(2);
				int lineNum = iter.nextToInt(2);
				LineNumberItem lineNumberItem = new LineNumberItem();
				lineNumberItem.setStartPC(startPc);
				lineNumberItem.setLineNum(lineNum);
				lineNumberTable.addLineNumberItem(lineNumberItem);
			}
		}
		return lineNumberTable;
	}
	
	

}
