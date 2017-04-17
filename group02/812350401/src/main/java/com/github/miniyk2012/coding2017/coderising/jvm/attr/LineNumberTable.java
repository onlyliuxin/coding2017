package com.github.miniyk2012.coding2017.coderising.jvm.attr;

import com.github.miniyk2012.coding2017.coderising.jvm.loader.ByteCodeIterator;

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
		
		int index = iter.nextU2toInt();
		int len = iter.nextU4toInt();
		
		LineNumberTable table = new LineNumberTable(index,len);
		
		int itemLen = iter.nextU2toInt();
		
		for(int i=1; i<=itemLen; i++){
			LineNumberItem item = new LineNumberItem();
			item.setStartPC(iter.nextU2toInt());
			item.setLineNum(iter.nextU2toInt());
			table.addLineNumberItem(item);
		}
		return table;
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
