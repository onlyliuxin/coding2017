package me.lzb.jvm.attr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZB on 2017/4/15.
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
