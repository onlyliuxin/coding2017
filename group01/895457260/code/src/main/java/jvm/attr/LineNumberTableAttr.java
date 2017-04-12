package jvm.attr;

import jvm.classfile.ConstantPool;
import jvm.util.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTableAttr extends AttributeInfo {
	private List<LineNumberItem> items = new ArrayList<>();
	
	private LineNumberTableAttr(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
	}

	public static LineNumberTableAttr parse(int attrNameIndex, int attrLen,
											ByteCodeIterator iterator, ConstantPool constantPool) {
		int tableLength = iterator.nextU2ToInt();

		LineNumberTableAttr result = new LineNumberTableAttr(attrNameIndex, attrLen);

		for (int i = 0; i < tableLength; ++i) {
			int startPC = iterator.nextU2ToInt();
			int lineNumber = iterator.nextU2ToInt();
			LineNumberItem item = new LineNumberItem(startPC, lineNumber);
			result.items.add(item);
		}
		return result;
	}

	public List<LineNumberItem> getItems() {
		return items;
	}

	public static class LineNumberItem {
		private int startPc;
		private int lineNumber;

		private LineNumberItem(int startPc, int lineNumber) {
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
