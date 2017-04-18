package jvm.classfile.attribute.item.impl;

import jvm.classfile.attribute.item.AttributeInfo;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTableAttr extends AttributeInfo {

	List<LocalVariableItem> items = new ArrayList<>();

	public LocalVariableTableAttr(int attrNameIndex, int attrLen, List<LocalVariableItem> items) {
		super(attrNameIndex, attrLen);
		this.items = items;
	}

	public static class LocalVariableItem {
		private int startPC;
		private int length;
		private int nameIndex;
		private int descIndex;
		private int index;

		public LocalVariableItem(int startPC, int length, int nameIndex, int descIndex, int index) {
			this.startPC = startPC;
			this.length = length;
			this.nameIndex = nameIndex;
			this.descIndex = descIndex;
			this.index = index;
		}

		public int getStartPC() {
			return startPC;
		}

		public int getLength() {
			return length;
		}

		public int getNameIndex() {
			return nameIndex;
		}

		public int getDescIndex() {
			return descIndex;
		}

		public int getIndex() {
			return index;
		}
	}
}
