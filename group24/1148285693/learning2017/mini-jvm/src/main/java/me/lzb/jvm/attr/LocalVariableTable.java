package me.lzb.jvm.attr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZB
 */
public class LocalVariableTable extends AttributeInfo {
    List<LocalVariableItem> items = new ArrayList<>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

}
