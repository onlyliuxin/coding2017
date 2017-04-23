package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

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

    public static LineNumberTable parse(ByteCodeIterator iter, int attribute_name_index) {
        int ccc = (iter.codes.length);
        long attribute_len = iter.nextU4ToInt();
        int line_number_table_len = iter.nextU2ToInt();
        LineNumberTable table = new LineNumberTable(attribute_name_index,(int)attribute_len);

        /*byte[] bytes = iter.getBytes(line_number_table_len);
        ByteCodeIterator lntIter = new ByteCodeIterator(bytes);*/


        /*while (lntIter.isNotEnd()) {
            //int c = lntIter.codes.length;
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(lntIter.nextU2ToInt());
            item.setLineNum(lntIter.nextU2ToInt());
            table.addLineNumberItem(item);
        }*/



        for (int i = 0; i <line_number_table_len ; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLineNum(iter.nextU2ToInt());
            table.addLineNumberItem(item);
        }
        return table;
    }

    public String toString(){
        StringBuilder buffer = new StringBuilder();
        buffer.append("Line Number Table:\n");
        for(LineNumberItem item : items){
            buffer.append("startPC:"+item.getStartPC()).append(",");
            buffer.append("lineNum:"+item.getLineNum()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();

    }


}
