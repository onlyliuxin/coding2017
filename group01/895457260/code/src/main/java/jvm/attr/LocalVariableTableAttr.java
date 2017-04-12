package jvm.attr;


import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTableAttr extends AttributeInfo {

	List<LocalVariableItem> items = new ArrayList<>();

	public LocalVariableTableAttr(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public static LocalVariableTableAttr parse(int attrNameIndex, int attrLen,
											   ByteCodeIterator iterator, ConstantPool constantPool) {
		int tableLen = iterator.nextU2ToInt();

		LocalVariableTableAttr result = new LocalVariableTableAttr(attrNameIndex, attrLen);

		for (int i = 0; i < tableLen; ++i) {
			int startPC = iterator.nextU2ToInt();
			int length = iterator.nextU2ToInt();
			int nameIndex = iterator.nextU2ToInt();
			int descIndex = iterator.nextU2ToInt();
			int index = iterator.nextU2ToInt();
			LocalVariableItem item = new LocalVariableItem(startPC, length, nameIndex, descIndex, index);
			result.items.add(item);
		}
		return result;
	}

	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);
	}

	public static class LocalVariableItem {
		private int startPC;
		private int length;
		private int nameIndex;
		private int descIndex;
		private int index;

		private LocalVariableItem(int startPC, int length, int nameIndex, int descIndex, int index) {
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
