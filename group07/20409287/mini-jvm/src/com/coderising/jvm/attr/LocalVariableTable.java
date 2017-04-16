package com.coderising.jvm.attr;

import com.coderising.jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudanxia on 2017/4/11.
 */
public class LocalVariableTable extends AttributeInfo{

    List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter){

        int attrIndex = iter.nextU2toInt();
        int attrLen = iter.nextU4ToInt();

        LocalVariableTable localVariableTable = new LocalVariableTable(attrIndex, attrLen);

        int tableLen = iter.nextU2toInt();
        for (int i = 0; i < tableLen; i++) {
            LocalVariableItem localVariableItem = new LocalVariableItem();
            localVariableItem.setStartPC(iter.nextU2toInt());
            localVariableItem.setLength(iter.nextU2toInt());
            localVariableItem.setNameIndex(iter.nextU2toInt());
            localVariableItem.setDescIndex(iter.nextU2toInt());
            localVariableItem.setIndex(iter.nextU2toInt());
            localVariableTable.addLocalVariableItem(localVariableItem);
        }

        return localVariableTable;
    }
    private void addLocalVariableItem(LocalVariableItem item) {
        this.items.add(item);
    }


}