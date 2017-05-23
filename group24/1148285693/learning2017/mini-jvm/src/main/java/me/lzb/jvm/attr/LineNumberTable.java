package me.lzb.jvm.attr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class LineNumberTable extends AttributeInfo {
    List<LineNumberItem> items = new ArrayList<>();


    public void addLineNumberItem(LineNumberItem item) {
        this.items.add(item);
    }

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);

    }


}
