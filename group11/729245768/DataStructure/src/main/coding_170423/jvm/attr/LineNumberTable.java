package main.coding_170423.jvm.attr;

import main.coding_170423.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 2017/4/21.
 */
public class LineNumberTable extends AttributeInfo {
    List<LineNumberItem> items = new ArrayList<>();

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

    public static LineNumberTable parse(ByteCodeIterator iterator) {
        int index = iterator.nextU2ToInt();
        int len = iterator.nextU4ToInt();

        LineNumberTable table = new LineNumberTable(index, len);
        for (int i = 1; i < len; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iterator.nextU2ToInt());
            item.setLineNum(iterator.nextU2ToInt());
            table.addLineNumberItem(item);
        }
        return table;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Line Number Table:\n");
        for(LineNumberItem item:items){
            sb.append("startPC:"+item.getStartPC()).append(",");
            sb.append("LineNum:"+item.getLineNum()).append("\n");
        }
        sb.append("\n");
        return  sb.toString();
    }
}
