package com.github.miniyk2012.coding2017.coderising.jvm.attr;

import com.github.miniyk2012.coding2017.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;


public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<>();
	
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
	
	public static LineNumberTable parse_V2(ByteCodeIterator iter){
		
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

	public static LineNumberTable parse(ByteCodeIterator iter){
        int nameIndex = iter.nextU2toInt();
        int attrLen = iter.nextU4toInt();
        int tableLen = iter.nextU2toInt();
        LineNumberTable table = new LineNumberTable(nameIndex, attrLen);

        for (int i=0; i<tableLen; i++) {
            int startPc = iter.nextU2toInt();
            int lineNum = iter.nextU2toInt();
            LineNumberItem lineNumberItem = new LineNumberItem();
            lineNumberItem.setStartPC(startPc);
            lineNumberItem.setLineNum(lineNum);
            table.addLineNumberItem(lineNumberItem);
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
