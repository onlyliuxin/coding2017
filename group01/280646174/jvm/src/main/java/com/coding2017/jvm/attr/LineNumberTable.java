package com.coding2017.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

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

    public static LineNumberTable parse(ClassFile clzFile, ByteCodeIterator iter, int nameIndex, int length) {
        int tableLength = iter.nextU2ToInt();
        LineNumberTable lineNumberTable = new LineNumberTable(nameIndex, length);
        for (int i = 0; i < tableLength; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLineNum(iter.nextU2ToInt());
            lineNumberTable.addLineNumberItem(item);
        }

        return lineNumberTable;
    }

}
