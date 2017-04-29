package com.coding2017.jvm.attr;

import com.coding2017.jvm.clz.ClassFile;
import com.coding2017.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ClassFile clzFile, ByteCodeIterator iter, int nameIndex, int length) {
        int tableLength = iter.nextU2ToInt();
        LocalVariableTable localVariableTable = new LocalVariableTable(nameIndex, length);
        for (int i = 0; i < tableLength; i++) {
            LocalVariableItem item = new LocalVariableItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLength(iter.nextU2ToInt());
            item.setNameIndex(iter.nextU2ToInt());
            item.setDescIndex(iter.nextU2ToInt());
            item.setIndex(iter.nextU2ToInt());
            localVariableTable.addLocalVariableItem(item);
        }
        return localVariableTable;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

}
