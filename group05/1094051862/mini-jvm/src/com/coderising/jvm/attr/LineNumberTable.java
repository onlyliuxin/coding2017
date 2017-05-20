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
		int attrNameIndex = iter.nextU2toInt();
		int attrLen = iter.nextU4toInt();
		
		LineNumberTable t = new LineNumberTable(attrNameIndex, attrLen);
		
		int linNumTableLen = iter.nextU2toInt();
		
		for(int i = 0; i < linNumTableLen; i++) {
			LineNumberItem m = new LineNumberItem();
			m.setStartPC(iter.nextU2toInt());
			m.setLineNum(iter.nextU2toInt());
			
			t.addLineNumberItem(m);
		}
		
		return t;
	}
	
	

}
