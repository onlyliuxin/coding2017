package com.coding.mini_jvm.src.com.coderising.jvm.attr;

import com.coding.mini_jvm.src.com.coderising.jvm.loader.ByteCodeIterator;

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
		iter.back(ByteCodeIterator.U2);
		int attrNameIndex = iter.readTwoBytesToInt();
		int attrLen = iter.readFourBytesToInt();
		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLen);
		int itemCount = iter.readTwoBytesToInt();
		for (int i = 0; i < itemCount; i++) {
			LineNumberItem lineNumberItem  = new LineNumberItem();
			lineNumberItem.setStartPC(iter.readTwoBytesToInt());
			lineNumberItem.setLineNum(iter.readTwoBytesToInt());
			lineNumberTable.addLineNumberItem(lineNumberItem);
		}
		return lineNumberTable;
	}
	
	

}
