package task7.jvm.attr;

import task7.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

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

        int index = iter.next2Bytes();
        int len = iter.next4Bytes();

        LineNumberTable table = new LineNumberTable(index, len);

        int itemLen = iter.next2Bytes();

        for (int i = 1; i <= itemLen; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.next2Bytes());
            item.setLineNum(iter.next2Bytes());
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
