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
	
	public  void parse(ByteCodeIterator iter){
		int count=iter.nextU2ToInt();
		for (int j = 0; j <count ; j++) {
			LineNumberItem	item=new LineNumberItem();
			item.setStartPC(iter.nextU2ToInt());
			item.setLineNum(iter.nextU2ToInt());
			this.addLineNumberItem(item);
			}
		
	}
	
	

}
