package com.donaldy.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.donaldy.jvm.loader.ByteCodeIterator;

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
		int attrNameIndex = iter.nextU2ToInt();
		int attributeLen = iter.nextU4ToInt();
		int lnTableLen = iter.nextU2ToInt();
		LineNumberTable lnTable = new LineNumberTable(attrNameIndex, attributeLen);

		System.out.println("LineNumberTable.lnTableLen : " + lnTableLen);

		for (int i = 0; i < lnTableLen; ++i) {
			LineNumberItem lnItem = new LineNumberItem();

			lnItem.setStartPC(iter.nextU2ToInt());
			lnItem.setLineNum(iter.nextU2ToInt());

			lnTable.addLineNumberItem(lnItem);
		}

		return lnTable;
	}
	
	

}
