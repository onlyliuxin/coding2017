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
		int nameIdx = iter.nextU2AsInt();
		int attrLen = iter.nextU4AsInt();
		LineNumberTable t = new LineNumberTable(nameIdx, attrLen);
		int itemCount = iter.nextU2AsInt();
		for(int i=0;i<itemCount;i++){
			LineNumberItem item = new LineNumberItem();
			item.setStartPC(iter.nextU2AsInt());
			item.setLineNum(iter.nextU2AsInt());
			t.addLineNumberItem(item);
		}
		return t;
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
