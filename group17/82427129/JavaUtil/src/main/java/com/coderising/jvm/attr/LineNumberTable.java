package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {

	private static String TYPE = LINE_NUM_TABLE;
	private int lineNumberTableLength;
	private List<LineNumberItem> lineNumberTable = new ArrayList<LineNumberTable.LineNumberItem>();

	public LineNumberTable(int attrNameIndex, int attrLen,
			int lineNumberTableLength) {
		super(attrNameIndex, attrLen);
		this.lineNumberTableLength = lineNumberTableLength;
	}

	public void addLineNumberItem(LineNumberItem e) {
		this.lineNumberTable.add(e);
	}

	@Override
	public String getType() {
		return TYPE;
	}
	public static LineNumberTable parse(ConstantPool pool, ByteCodeIterator itr) {
		int attrNameIndex = itr.nextU2toInt();
		int attrLen = itr.nextU4toInt();
		int lineNumberTableLength = itr.nextU2toInt();
		LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex,
				attrLen, lineNumberTableLength);
		for (int i = 0; i < lineNumberTableLength; i++) {
			lineNumberTable.addLineNumberItem(LineNumberItem.parse(pool, itr));
		}
		return lineNumberTable;
	}

	/**
	 *  
	 * @author Meng
	 *
	 */
	private static class LineNumberItem {
		@SuppressWarnings("unused")
		private int startPc;
		@SuppressWarnings("unused")
		private int lineNum;

		public LineNumberItem(int startPc, int lineNum) {
			super();
			this.startPc = startPc;
			this.lineNum = lineNum;
		}

		public static LineNumberItem parse(ConstantPool pool,
				ByteCodeIterator itr) {
			int startPc = itr.nextU2toInt();
			int lineNum = itr.nextU2toInt();
			return new LineNumberTable.LineNumberItem(startPc, lineNum);
		}
	}

	/*
	 * getter setter
	 */
	public int getLineNumberTableLength() {
		return lineNumberTableLength;
	}

	public void setLineNumberTableLength(int lineNumberTableLength) {
		this.lineNumberTableLength = lineNumberTableLength;
	}

}
