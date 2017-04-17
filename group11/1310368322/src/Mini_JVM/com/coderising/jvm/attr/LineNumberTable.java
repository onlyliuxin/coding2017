package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo{

	List<LineNumberItem> items = new ArrayList<LineNumberItem>();// ����кű����кܶ�ġ��кŶ�Ӧ��

	// ������LineNumberTable��һ���ṹ�������ñ��˿���
	private static class LineNumberItem{
		int startPC;// ��¼�ֽ����к�
		int lineNum;// ��¼JavaԴ������к�
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
	
	public static LineNumberTable parse(ByteCodeIterator iter, int subAttrNameIndex, int subAttrLen){
		LineNumberTable lineNumTable = new LineNumberTable(subAttrNameIndex, subAttrLen);
		int lineNumTableLen = iter.nextU2toInt();
		for(int i = 0; i < lineNumTableLen; i ++){
			int startPC = iter.nextU2toInt();
			int lineNum = iter.nextU2toInt();
			LineNumberItem lineNumItem = new LineNumberItem();
			lineNumItem.setStartPC(startPC);
			lineNumItem.setLineNum(lineNum);
			lineNumTable.addLineNumberItem(lineNumItem);
		}
		return lineNumTable;
	}

}
