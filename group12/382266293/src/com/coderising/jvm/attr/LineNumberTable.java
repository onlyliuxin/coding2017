package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public int getLineNum() {
			return lineNum;
		}
		public int getStartPC() {
			return startPC;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
	}
	
	public static LineNumberTable parse(ByteCodeIterator iter){
		
		int attrNameIndex = iter.nextU2ToInt();
		 
		int attrLen = iter.nextU4ToInt();
		 
		int line_number_table_length = iter.nextU2ToInt();
		
		LineNumberTable lnt = new LineNumberTable(attrNameIndex, attrLen);
		
		
		for (int i = 0; i < line_number_table_length; i++) {
			
			LineNumberItem lni = new LineNumberItem();
			
			int start_pc = iter.nextU2ToInt();
			lni.setStartPC(start_pc);
			
			int line_number = iter.nextU2ToInt();
			lni.setLineNum(line_number);
			
			lnt.addLineNumberItem(lni);
		}
		 


		return lnt;
	}
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	
	

}
