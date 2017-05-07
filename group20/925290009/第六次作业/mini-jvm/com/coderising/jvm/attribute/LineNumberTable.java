package com.coderising.jvm.attribute;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clasfile.ClassFile;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo{
	
	private List<LineNumberItem> lineNumberItems = new ArrayList<>();

	private static class LineNumberItem{
		
		private int startPc;
		private int lineNum;
		
		public int getStartPc() {
			return startPc;
		}
		public void setStartPc(int startPc) {
			this.startPc = startPc;
		}
		public int getLineNum() {
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
		
	}
	
	public void addLineNumberItem(LineNumberItem lineNumberItem){
		this.lineNumberItems.add(lineNumberItem);
	}
	
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}

	public static LineNumberTable parse(ByteCodeIterator iterator) {
		iterator.back(2);
		int attrIndex = iterator.next2BytesToInt();
		int attrlen = iterator.next4BytesToInt();
		LineNumberTable lineNumberTable = new LineNumberTable(attrIndex, attrlen);
		int lineNumberCount = iterator.next2BytesToInt();
		
		if (lineNumberCount > 0) {
			
			for (int i = 0; i < lineNumberCount; i++) {
				LineNumberItem lItem = new LineNumberItem();
				lItem.setStartPc(iterator.next2BytesToInt());
				lItem.setLineNum(iterator.next2BytesToInt());
				lineNumberTable.addLineNumberItem(lItem);
			}
		}
		
		return lineNumberTable;
	}
	
}
