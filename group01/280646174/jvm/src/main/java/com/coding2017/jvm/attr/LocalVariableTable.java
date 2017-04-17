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

        return null;
    }

    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }

}
