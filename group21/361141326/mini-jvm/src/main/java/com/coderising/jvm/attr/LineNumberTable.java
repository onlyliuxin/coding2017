package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTable extends AttributeInfo {

    List<LineNumberItem> items = new ArrayList<LineNumberItem>();

    public void addLineNumberItem(LineNumberItem item) {
        this.items.add(item);
    }

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);

    }

    public static LineNumberTable parse(ByteCodeIterator iterator) {
        int attributeNameIndex = iterator.nextU2ToInt();
        int attributeLength = iterator.nextU4ToInt();
        int lineNumberTableLength = iterator.nextU2ToInt();

        LineNumberTable lineNumberTable = new LineNumberTable(attributeNameIndex, attributeLength);

        for (int i = 0; i < lineNumberTableLength; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iterator.nextU2ToInt());
            item.setLineNum(iterator.nextU2ToInt());

            lineNumberTable.addLineNumberItem(item);
        }

        return lineNumberTable;
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

    private static class LineNumberItem {

        // code[] 数组中的一个索引，
        int startPC;

        // 与源文件的行数相匹配
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
}
