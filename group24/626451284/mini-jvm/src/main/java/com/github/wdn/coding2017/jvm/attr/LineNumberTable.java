package com.github.wdn.coding2017.jvm.attr;

import com.github.wdn.coding2017.jvm.constant.ConstantPool;
import com.github.wdn.coding2017.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */
public class LineNumberTable extends AttributeInfo{
    public LineNumberTable(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }
    List<LineNumberItem> lineNumberItems = new ArrayList<>();
    private static class LineNumberItem{
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
    public void addLineNumberItem(LineNumberItem item){
        this.lineNumberItems.add(item);
    }

    public static LineNumberTable parse(ByteCodeIterator iter){
        LineNumberTable lineNumberTable = new LineNumberTable(0, iter.readU4ToInt());
        int lineNumberTableCount = iter.readU2ToInt();
        for (int l = 0; l < lineNumberTableCount; l++) {
            LineNumberItem lineNumberItem = new LineNumberItem();
            lineNumberItem.setStartPC(iter.readU2ToInt());
            lineNumberItem.setLineNum(iter.readU2ToInt());
            lineNumberTable.addLineNumberItem(lineNumberItem);
        }
        return lineNumberTable;
    }
}
