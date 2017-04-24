package com.github.HarryHook.coding2017.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.github.HarryHook.coding2017.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {
    List<LineNumberItem> items = new ArrayList<LineNumberItem>();

    private static class LineNumberItem {
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

    public void addLineNumberItem(LineNumberItem item) {
	this.items.add(item);
    }

    public LineNumberTable(int attrNameIndex, int attrLen) {
	super(attrNameIndex, attrLen);

    }

    public static LineNumberTable parse(ByteCodeIterator iter) {
	int attrNameIndex = iter.nextU2ToInt();
	int attrLength = iter.nextU4ToInt();
	LineNumberTable table = new LineNumberTable(attrNameIndex, attrLength);
	int itemLength = iter.nextU2ToInt();

	for (int i = 1; i <= itemLength; i++) {
	    LineNumberItem item = new LineNumberItem();
	    item.setStartPC(iter.nextU2ToInt());
	    item.setLineNum(iter.nextU2ToInt());
	    table.addLineNumberItem(item);
	}

	return table;
    }

    public String toString() {
	StringBuilder buffer = new StringBuilder();
	buffer.append("Line Number Table:\n");
	for (LineNumberItem item : items) {
	    buffer.append("startPC:" + item.getStartPC()).append(",");
	    buffer.append("lineNum:" + item.getLineNum()).append("\n");
	}
	buffer.append("\n");
	return buffer.toString();

    }

}