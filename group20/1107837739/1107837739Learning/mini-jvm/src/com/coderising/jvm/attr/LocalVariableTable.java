package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;
import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter) {
        int attributeNameIndex = iter.nextU2ToInt();
        int attributeLen = iter.nextU4ToInt();
        int itemLen = iter.nextU2ToInt();

        LocalVariableTable variableTable = new LocalVariableTable(attributeNameIndex, attributeLen);
        for (int i = 0; i < itemLen; i++) {
            LocalVariableItem item = new LocalVariableItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLength(iter.nextU2ToInt());
            item.setNameIndex(iter.nextU2ToInt());
            item.setDescIndex(iter.nextU2ToInt());
            item.setIndex(iter.nextU2ToInt());

            variableTable.addLocalVariableItem(item);
        }
        return variableTable;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }
}
