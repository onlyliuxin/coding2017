package com.github.ipk2015.coding2017.minijvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.github.ipk2015.coding2017.minijvm.loader.ByteCodeIterator;



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
		int attrNameIndex = iter.nextUNToInt(2);
		int attrLength = iter.nextUNToInt(4);
		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex,attrLength);
		int lineNumTableLen = iter.nextUNToInt(2);
		for(int i = 0;i < lineNumTableLen;i++){
			LineNumberItem lineNumberItem = new LineNumberItem();
			lineNumberItem.setStartPC(iter.nextUNToInt(2));
			lineNumberItem.setLineNum(iter.nextUNToInt(2));
			lineNumberTable.addLineNumberItem(lineNumberItem);
		}
		return lineNumberTable;
	}
	
	

}
