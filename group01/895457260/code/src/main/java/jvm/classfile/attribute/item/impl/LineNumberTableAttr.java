package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTableAttr extends AttributeInfo {
	private List<LineNumberItem> items = new ArrayList<>();
	
	public LineNumberTableAttr(int attrNameIndex, int attrLen, List<LineNumberItem> items) {
		super(attrNameIndex, attrLen);
		this.items = items;
	}

	public List<LineNumberItem> getItems() {
		return items;
	}

	public static class LineNumberItem {
		private int startPc;
		private int lineNumber;

		public LineNumberItem(int startPc, int lineNumber) {
			this.startPc = startPc;
			this.lineNumber = lineNumber;
		}

		public int getStartPc() {
			return startPc;
		}

		public int getLineNumber() {
			return lineNumber;
		}
	}
}
