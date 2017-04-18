package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;
import java.util.ArrayList;
import java.util.List;

public class LineNumberTable extends AttributeInfo {
    List<LineNumberItem> items = new ArrayList<LineNumberItem>();

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LineNumberTable parse(ByteCodeIterator iter) {
        int attributeNameIndex = iter.nextU2ToInt();
        int attributeLen = iter.nextU4ToInt();
        int itemLen = iter.nextU2ToInt();

        LineNumberTable numberTable = new LineNumberTable(attributeNameIndex, attributeLen);

        for (int i = 0; i < itemLen; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLineNum(iter.nextU2ToInt());

            numberTable.addLineNumberItem(item);
        }
        return numberTable;
    }

    public void addLineNumberItem(LineNumberItem item) {
        this.items.add(item);
    }

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
}
