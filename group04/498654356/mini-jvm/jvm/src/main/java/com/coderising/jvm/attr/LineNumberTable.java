package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;


public class LineNumberTable extends AttributeInfo {
	private List<LineNumberItem> lineNumberItems = new ArrayList<>();

	public LineNumberTable(int attrNameIndex, int attrLength) {
		super(attrNameIndex, attrLength);
	}
	
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

	public List<LineNumberItem> getLineNumberItems() {
		return lineNumberItems;
	}
	
	public void addLineNumberItem(LineNumberItem item){
		this.lineNumberItems.add(item);
	}


	public static LineNumberTable parse(ByteCodeIterator it, ConstantPool constantPool) {
		int attrNameIndex = it.next2ByteToInt();
		int attrLength = it.next4ByteToInt();
		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLength);
		int lineNumberTableLength = it.next2ByteToInt();
		while(lineNumberTableLength > 0) {
			LineNumberItem item = new LineNumberItem();
			item.setStartPC(it.next2ByteToInt());
			item.setLineNum(it.next2ByteToInt());
			lineNumberTable.addLineNumberItem(item);
			lineNumberTableLength--;
		}
		return lineNumberTable;
	}

}
