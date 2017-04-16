package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudanxia on 2017/4/11.
 */
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

        int attrNameIndex = iter.nextU2toInt();
        int attrLen = iter.nextU4ToInt();

        LineNumberTable lineNumberTable = new LineNumberTable(attrNameIndex, attrLen);

        int iterLen = iter.nextU2toInt();

        for (int i = 0; i < iterLen; i++) {
            int startPc = iter.nextU2toInt();
            int lineNum = iter.nextU2toInt();
            LineNumberItem lineNumberItem = new LineNumberItem();
            lineNumberItem.setStartPC(startPc);
            lineNumberItem.setLineNum(lineNum);
            lineNumberTable.addLineNumberItem(lineNumberItem);
        }

        return lineNumberTable;
    }


}